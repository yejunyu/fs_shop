// pages/shop/mine/mine.js
var app = getApp()
Page({
  data: {
    userInfo: wx.getStorageSync('userinfo'),
    fromname:"mine",
    mine_list: [
      {
        "pic_url": "/images/icons/mine_01.png",
        "title": "我的订单",
        "key": "order",
      },
      {
        "pic_url": "/images/icons/mine_02.png",
        "title": "收货地址",
        "key": "address",
      },
      {
        "pic_url": "/images/icons/mine_03.png",
        "title": "代办列表",
        "key": "agency",
      },
      {
        "pic_url": "/images/icons/mine_04.png",
        "title": "我的反馈",
        "key": "feedback",
      },
      {
        "pic_url": "",
        "title": "",
        "key": "",
      },
      {
        "pic_url": "",
        "title": "",
        "key": "",
      }
    ]
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数

  },
  onReady: function () {

    // 页面渲染完成
  },
  onShow: function () {
    if (this.data.userInfo == '') {
      this.setData({
        'item.signinHidden': false
      })
    }

  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  },
  menuclick(e){
    let k = e.currentTarget.dataset.key;
    console.log("--->"+k);
    if(k=="order"){
      wx.navigateTo({
        url: 'order/list'
      });
    } else if (k == "address"){
      wx.setStorage({
        key: app.storageKey.addressListCatchtap,
        data: "addrEdit"
      });
      wx.navigateTo({
        url: 'address/address'
      });
    } else if (k == 'agency') {
      wx.navigateTo({
        url: '../agency/list'
      });
    } else if (k == 'feedback') {
      wx.navigateTo({
        url: '../sys/feedback/list'
      });
    } 
    
  }
})