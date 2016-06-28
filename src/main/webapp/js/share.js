(function share(){
    !("WeixinJSBridge" in window) ? document.addEventListener("WeixinJSBridgeReady", weixinReady, false) : weixinReady();
    function weixinReady() {
        // 发送给朋友
        WeixinJSBridge.on("menu:share:appmessage", shareFriends);
        // 发送到朋友圈分享
        WeixinJSBridge.on("menu:share:timeline", shareFriendCycle);
    }
    function shareFriends() {
        WeixinJSBridge.invoke("sendAppMessage", {
            img_url: "http://iot.weixin.qq.com/join/images/wechat_logo.png",
            img_width: "640",
            img_height: "640",
            link: location.href,
            desc: "微信硬件平台为您提供十大行业解决方案的全方位支持",
            title: document.title
        }, function() {})
    }
    function shareFriendCycle() {
        WeixinJSBridge.invoke("shareTimeline", {
            img_url: "http://iot.weixin.qq.com/join/images/wechat_logo.png",
            img_width: "640",
            img_height: "640",
            link: location.href,
            desc:"",
            title: document.title
        }, function () {})
    }
})();