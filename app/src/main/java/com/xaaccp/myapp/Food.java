package com.xaaccp.myapp;

import java.nio.ByteBuffer;

/**
 * <pre>
 *     author : growu
 *     e-mail : xxx@xx
 *     time   : 2022/12/04
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class Food {
    private String getOpenLockerCOM(int doorNumber, String deleverMode) {
        int tmp = 0xffff;
        char[] sendData = new char[8];
        sendData[0] = 0x00;
        sendData[1] = 0x05;
        sendData[2] = 0x00;
        String hex = null;
        //弹簧出货
        if (deleverMode.equals("SPRING")) {
            hex = StringUtil.toHex(doorNumber, 2).toUpperCase();
            //履带出货
        } else if (deleverMode.equals("BELT")) {
            hex = StringUtil.toHex(60 + doorNumber, 2).toUpperCase();
        }
        sendData[3] = (char) (Integer.parseInt(hex, 16) & 0xff);
        sendData[4] = 0xFF;
        sendData[5] = 0x00;
        for (int n = 0; n < 6; n++) {
            tmp = sendData[n] ^ tmp;
            for (int i = 0; i < 8; i++) {
                if ((tmp & 0x01) != 0) {
                    tmp = tmp >> 1;
                    tmp = tmp ^ 0xa001;
                } else {
                    tmp = tmp >> 1;
                }
            }
        }
        tmp = ((tmp & 0xFF00) >> 8) | ((tmp & 0x00FF) << 8);
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(tmp);
        sendData[6] = buffer.getChar(1);
        sendData[7] = buffer.getChar(2);
        StringBuffer sb = new StringBuffer(sendData.length);
        String sTemp;
        for (int i = 0; i < sendData.length; i++) {
            sTemp = Integer.toHexString(0xFF & sendData[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    //出货命令
    private String getOpenLockerCOM2(int doorNumber, String deleverMode) {
        int checkSum = 0;
        byte[] sendData = new byte[8];
        //头码
        sendData[0] = (byte) 0xA5;
        //地址码
        sendData[1] = 0x01;
        //指令码
        sendData[2] = 0x30;
        //弹簧出货
        if (deleverMode.equals("SPRING")) {
            sendData[2] = 0x30;
            //履带出货
        } else if (deleverMode.equals("BELT")) {
            sendData[2] = 0x31;
        }
        //标志位
        sendData[3] = 0x00;
        //数据长度
        sendData[4] = 0x01;
        //数据位
        sendData[5] = (byte) (doorNumber & 0xFF);
        //效验位
        for (int i = 0; i < 6; i++) {
            checkSum += (sendData[i] & 0xFF);
        }
        //校验位1
        sendData[6] = (byte) ((checkSum & 0xFF00) >> 8);
        //校验位2
        sendData[7] = (byte) (checkSum & 0x00FF);
        StringBuffer sb = new StringBuffer(sendData.length);
        String sTemp;
        for (int i = 0; i < sendData.length; i++) {
            sTemp = Integer.toHexString(0xFF & sendData[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }
}
