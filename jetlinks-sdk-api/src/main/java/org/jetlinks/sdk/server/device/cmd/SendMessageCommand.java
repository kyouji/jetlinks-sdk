package org.jetlinks.sdk.server.device.cmd;

import org.jetlinks.core.command.AbstractCommand;
import org.jetlinks.core.message.DeviceMessage;
import org.jetlinks.core.message.MessageType;
import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * 发送消息给设备
 * @see org.jetlinks.core.message.DeviceMessage
 */
public class SendMessageCommand extends AbstractCommand<Flux<DeviceMessage>, SendMessageCommand> {

    @SuppressWarnings("all")
    public DeviceMessage getMessage() {
        Object msg = readable().get("message");
        if (msg instanceof DeviceMessage) {
            return (DeviceMessage) msg;
        }
        if (msg instanceof Map) {
            return (DeviceMessage) MessageType
                    .convertMessage(((Map) msg))
                    .orElse(null);
        }
        return null;
    }

    public SendMessageCommand withMessage(Map<String, Object> message) {
        writable().put("message", message);
        return castSelf();
    }

    public SendMessageCommand withMessage(DeviceMessage message) {
        writable().put("message", message);
        return castSelf();
    }

}
