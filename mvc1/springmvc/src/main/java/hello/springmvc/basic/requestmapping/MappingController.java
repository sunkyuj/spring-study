package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/hello-basic") // {} 써서 여러개도 가능, 뒤에 슬래쉬 붙여도 같은 url
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET) // 여러개도 가능, 뒤에 슬래쉬 붙여도 같은 url
    public String mappingGetV1() {
        log.info("mapping-get-v1");
        return "ok";
    }

    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    @GetMapping("/mapping/{userId}") //PathVariable
    public String mappingPath(@PathVariable String userId) {
        log.info("mappingPath userId={}", userId);
        return "ok";
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}") //multi PathVariable
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}", userId);
        log.info("mappingPath orderId={}", orderId);
        return "ok";
    }

    @GetMapping(value = "/mapping-param", params = "mode=debug") //parameter mapping
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    @GetMapping(value = "/mapping-header", headers = "mode=debug") //header mapping
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE) //media type mapping: consume (content-type 헤더 기반), 서버야 ~으로 줄게
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE) //media type mapping: produce (Access 헤더 기반), 서버야 ~만 줘라
    public String mappingProduce() {
        log.info("mappingProduce");
        return "ok";
    }
}
