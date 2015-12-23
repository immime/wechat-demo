package cc.wechat.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.wechat.sdk.api.config.ConfigChangeNotice;
import cc.wechat.sdk.handle.AbstractApiConfigChangeHandle;

public class TestConfigChangeHandle extends AbstractApiConfigChangeHandle {

	private static final Logger LOG = LoggerFactory.getLogger(TestConfigChangeHandle.class);

    @Override
    public void configChange(ConfigChangeNotice notice) {
        LOG.debug("收到wechat配置改变通知.....");
        LOG.debug(notice.toJsonString());
    }

}
