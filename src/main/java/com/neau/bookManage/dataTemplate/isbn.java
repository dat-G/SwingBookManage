package com.neau.bookManage.dataTemplate;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class isbn {
    public static boolean isValidISBN(String code) {
        String tmpCode = code.replaceAll("x", "X").replaceAll("[^0-9X]+", "");
        char checkDigit = tmpCode.charAt(tmpCode.length() - 1);
        String numString = tmpCode.substring(0, tmpCode.length() - 1);
        if (numString.length() > 9) return false; // 长度错误
        Integer ISBNnum;
        try {
            ISBNnum = Integer.parseInt(numString);
        } catch (NumberFormatException e) { // 字符错误
            return false;
        }
        Integer ISBNCheckSum = 0;
        for (int i = 2; i <= 10; i++) {
            ISBNCheckSum += (ISBNnum % 10) * i;
            ISBNnum /= 10;
        }
        if (11 - ISBNCheckSum % 11 == (int) checkDigit - (int) '0')
            return true;
        else if ((11 - ISBNCheckSum % 11 == 10) && (checkDigit == 'X'))
            return true;
        else return false;
    }
    public static void dynISBNcomplete(JTextField jt_isbn) {
//                System.out.println("Changed.");
        int caretShift = 0;
//                System.out.println(caretPos);
        String fieldText = jt_isbn.getText();
        String tmpFieldText = fieldText.replaceAll("x", "X").replaceAll("[^0-9X]+", ""); // 过滤非数字字符
        if (tmpFieldText.length() > 10) {
            tmpFieldText = tmpFieldText.substring(0, 10);
        } // 长度过滤
        int fieldLen = tmpFieldText.length();
        StringBuffer dashStr = new StringBuffer(tmpFieldText);
        if (fieldLen >= 1) {
            dashStr.insert(1, "-");
            caretShift++;
        }
        if (fieldLen >= 5) {
            dashStr.insert(6, "-");
            caretShift++;
        }
        if (fieldLen >= 9) {
            dashStr.insert(11, "-");
            caretShift++;
        }
        tmpFieldText = dashStr.toString();
        if (!fieldText.equals(tmpFieldText)) {
            String finalTmpFieldText = tmpFieldText;
            int caretDashTweak = 0;
            if (finalTmpFieldText.endsWith("-")) caretDashTweak = 1;
            int finalCaretPos = fieldLen + caretShift - caretDashTweak;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    jt_isbn.setText(finalTmpFieldText);
                    jt_isbn.requestFocus();
                    jt_isbn.setCaretPosition(finalCaretPos);
                }
            }).start();
        }
    }
}
