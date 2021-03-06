//package com.darkwater.service;
//
///**
// * Created by Mr.Darkwater on 2017/7/27.
// */
//
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.stereotype.Service;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class SensitiveService implements InitializingBean {
//
//    private static Logger logger = LoggerFactory.getLogger(SensitiveService.class);
//
//    private TrieNode rootNode = new TrieNode();
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        try {
//            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("SensitiveWords.txt");
//            InputStreamReader reader = new InputStreamReader(is);
//            BufferedReader bufferedReader = new BufferedReader(reader);
//            String lineTxt;
//            while (null != (lineTxt = bufferedReader.readLine())) {
//                addWord(lineTxt.trim());
//            }
//            reader.close();
//
//        } catch (Exception e) {
//            logger.error("读取敏感词文件失败" + e.getMessage());
//        }
//    }
//
//    public String filter(String text) {
//
//        if (StringUtils.isBlank(text)) {
//            return text;
//        }
//
//        StringBuilder result = new StringBuilder();
//        String replacement = "***";
//        TrieNode tempNode = rootNode;
//        int begin = 0;
//        int position = 0;
//
//        while (position < text.length()) {
//            char c = text.charAt(position);
//
//            tempNode = tempNode.getSubNode(c);
//
//            if (null == tempNode) {
//                result.append(text.charAt(begin));
//                position = begin + 1;
//                begin = position;
//                tempNode = rootNode;
//            } else if (tempNode.isKeyEnd()) {
//                //发现敏感词
//                result.append(replacement);
//                position = position + 1;
//                begin = position;
//                tempNode = rootNode;
//            } else {
//                position = position + 1;
//            }
//        }
//
//        result.append(text.substring(begin));
//        return result.toString();
//    }
//
//    /**
//     * 增加关键词
//     *
//     * @param lineText 敏感词
//     */
//    private void addWord(String lineText) {
//        TrieNode tempNode = rootNode;
//        for (int i = 0; i < lineText.length(); ++i) {
//            Character c = lineText.charAt(i);
//
//            TrieNode node = tempNode.getSubNode(c);
//            if (null == node) {
//                node = new TrieNode();
//                tempNode.addSubNode(c, node);
//            }
//            tempNode = node;
//
//            if ((lineText.length() - 1) == i) {
//                tempNode.setKeywordEnd(true);
//            }
//        }
//    }
//
//    private class TrieNode {
//        private boolean end = false;
//
//        private Map<Character, TrieNode> subNodes = new HashMap<>();
//
//        public void addSubNode(Character key, TrieNode node) {
//            subNodes.put(key, node);
//        }
//
//        TrieNode getSubNode(Character key) {
//            return subNodes.get(key);
//        }
//
//        boolean isKeyEnd() {
//            return end;
//        }
//
//        void setKeywordEnd(boolean end) {
//            this.end = end;
//        }
//    }
//
//    public static void main(String[] args) {
//        SensitiveService s = new SensitiveService();
//        s.addWord("赌博");
//        s.addWord("外挂");
//        System.out.println(s.filter("你好赌博啊啊啊外挂"));
//    }
//}
//
