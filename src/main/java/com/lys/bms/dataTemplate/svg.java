package com.lys.bms.dataTemplate;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.bridge.*;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGElement;

import javax.swing.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;

public class svg {
    public static Image getSVGImg(String svgPath, String renderColor) throws IOException {
        BufferedImage tmpSVGImg = loadSvgImage(svgPath, renderColor);
        ImageIcon tmpIcon = new ImageIcon(tmpSVGImg);
        return tmpIcon.getImage();
    }


    public static BufferedImage loadSvgImage(String resourceName, String renderColor) throws IOException {
        System.out.println(renderColor);
        URL resourceURL = svg.class.getResource(resourceName);
        // 创建SVG文档工厂
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);

//        factory.getClass("fill") = ;

        // 加载SVG文档
        System.out.println("file:"+resourceURL.getPath());
        SVGDocument svgDocument = factory.createSVGDocument("file:"+resourceURL.getPath());
        SVGElement rootElement = svgDocument.getRootElement();

        rootElement.setAttributeNS(null, "fill", renderColor);

//        System.out.println(convertDocumentToString(svgDocument));
        float width = Float.parseFloat(rootElement.getAttributeNS(null, "width"));
        float height = Float.parseFloat(rootElement.getAttributeNS(null, "height"));

        // 创建用户代理
        UserAgent userAgent = new UserAgentAdapter();
        // 创建DocumentLoader
        DocumentLoader documentLoader = new DocumentLoader(userAgent);
        // 创建BridgeContext
        BridgeContext bridgeContext = new BridgeContext(userAgent, documentLoader);
        GVTBuilder builder = new GVTBuilder();

        // 将SVG文档转换为GraphicsNode
        GraphicsNode graphicsNode = builder.build(bridgeContext, svgDocument);

        // 获取SVG图像的尺寸

        // 创建一个BufferedImage来绘制SVG图像
        BufferedImage bufferedImage = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_ARGB);

        // 创建Graphics2D对象并绘制SVG图像
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setColor(new Color(255,0,0));
        graphicsNode.paint(g2d);
        g2d.dispose();

        return bufferedImage;
    }
}
