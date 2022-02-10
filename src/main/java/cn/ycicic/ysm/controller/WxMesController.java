package cn.ycicic.ysm.controller;

import cn.ycicic.ysm.config.WxCpConfiguration;
import cn.ycicic.ysm.dto.YsmMes;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpMessageService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpMessageServiceImpl;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import org.springframework.web.bind.annotation.*;

/**
 * @author ycc
 */
@Slf4j
@RestController
@RequestMapping("/wx/cp/message/{agentId}")
public class WxMesController {

    @PostMapping(value = "/send", produces = "application/json; charset=UTF-8")
    public void send(@PathVariable Integer agentId, @RequestBody YsmMes ysmMes) {

        final WxCpService wxCpService = WxCpConfiguration.getCpService(agentId);
        WxCpMessageService wxCpMessageService = new WxCpMessageServiceImpl(wxCpService);
        WxCpMessage message = WxCpMessage.TEXT().agentId(agentId).toParty("1").content(ysmMes.getText()).build();
        try {
            wxCpMessageService.send(message);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

}
