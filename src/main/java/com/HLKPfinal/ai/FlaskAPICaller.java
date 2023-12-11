//package com.HLKPfinal.ai;
//
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//public class FlaskAPICaller {
//    public static void main(String[] args) {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:5000/predict"; // Flask 서버의 URL
//
//        // 파일을 전송하기 위한 MultiValueMap을 만듭니다.
//        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
//        bodyMap.add("file", new FileSystemResource("path/to/your/file"));
//
//        // 헤더를 설정합니다.
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//
//        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);
//
//        // Flask 서버에 POST 요청을 보냅니다.
//        String response = restTemplate.postForObject(url, requestEntity, String.class);
//
//        // 결과를 출력합니다.
//        System.out.println(response);
//    }
//}