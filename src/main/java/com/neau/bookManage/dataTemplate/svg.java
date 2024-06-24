package com.neau.bookManage.dataTemplate;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.bridge.*;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGElement;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class svg {
    public static Image getSVGImg(String svgPath, String renderColor, int width, int height) throws IOException {
        BufferedImage tmpSVGImg = loadSvgImage(svgPath, renderColor, 512, 512);
        Image tmpSmooth = tmpSVGImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//        ImageIcon tmpIcon = new ImageIcon(tmpSVGImg);
        return tmpSmooth;
    }

    public static ImageIcon getSVGIcon(String svgPath, String renderColor, int width, int height) throws IOException {
        BufferedImage tmpSVGImg = loadSvgImage(svgPath, renderColor, 512, 512);
        Image tmpSmooth = tmpSVGImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon tmpIcon = new ImageIcon(tmpSmooth);
        return tmpIcon;
    }

    public static BufferedImage loadSvgImage(String resourceName, String renderColor, int width, int height) throws IOException {
        URL resourceURL = svg.class.getResource(resourceName);
        // 创建SVG文档工厂
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);

//        factory.getClass("fill") = ;

        // 加载SVG文档
        SVGDocument svgDocument = factory.createSVGDocument("file:"+resourceURL.getPath());
        SVGElement rootElement = svgDocument.getRootElement();

        rootElement.setAttributeNS(null, "fill", renderColor);
        rootElement.setAttributeNS(null, "width", String.valueOf(width));
        rootElement.setAttributeNS(null, "height", String.valueOf(height));

//        System.out.println(convertDocumentToString(svgDocument));
//        float width = Float.parseFloat(rootElement.getAttributeNS(null, "width"));
//        float height = Float.parseFloat(rootElement.getAttributeNS(null, "height"));

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
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // 创建Graphics2D对象并绘制SVG图像
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setColor(new Color(255,0,0));
        graphicsNode.paint(g2d);
        g2d.dispose();

        return bufferedImage;
    }
}
