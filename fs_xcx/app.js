//app.js
App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs);
    // 登录
    wx.login({
      success: function (res) {
        if (res.code) {
          //发起网络请求
          var url = 'https://api.weixin.qq.com/sns/jscode2session';
          var appid = 'wx8f3f2adb2038cc7c'; //填写微信小程序appid  
          var secret = 'f3a8128894edb194de71e4cfabaecefb'; //填写微信小程序secret  
          url += '?appid=' + appid + '&secret=' + secret + '&grant_type=authorization_code&js_code=' + res.code;
          wx.request({
            url: url,
            success: function (res) {
              console.log('openid-->'+res.data.openid) //获取openid  
              wx.setStorageSync('openid', res.data.openid);
              var d = { 'wxId': res.data.openid};
              var url = 'http://localhost:8888' + "/member/createMemberByWxId";
              wx.request({
                url: url, //
                data: d,
                success: function (res) {
                  wx.setStorageSync('userid', res.data.result.id);
                  console.log("微信用户保存成功");
                }
              });
            }
          })  
        } else {
          console.log('获取用户登录态失败！' + res.errMsg)
        }
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo;
              wx.setStorageSync('userinfo', res.userInfo);
              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  //参数统一过滤处理
  paramsFilter(data){
    var ndata = this.fieldRemove(data);
    
    return ndata;
  },
  fieldRemove(data){  //移除返回的不需要的参数
    var ndata = [];
    for (var key in data) {
      if (key != "createBy" && key != "createDate" &&
        key != "updateBy" && key != "updateDate" &&
        key != "extraData") {
        ndata[key] = data[key];
      }
    }
    return ndata;
  },
  globalData: {
    userInfo: null,
    openid:''
  },
  common:{
    basePath:"http://192.168.1.120:8888"
  },
  storageKey: {
    cart: "cart_list",                              // 购物车列表
    goodsTempId: "goods_temp_id",                   // 当前商品类型id
    addressCurrent: "menber_address_current",       // 当前选中地址
    addressList: "menber_address_list",             // 地址列表
    addressListCatchtap: "address_list_catchtap",   // 地址列表点击事件 addrSelect 选中 addrEdit 修改
    agencyCurrent:"agency_currnt",                  // 当前代办对象的值
    feedbackCurrent:"feedback_current",
    

    demo:"demo"
  }
})