//获取应用实例
var app = getApp()

Page({
  data: {
    localtion: '广州市政府',
    interval: 3000,
    duration: 1000,


    motto: 'MiHome_Store',
    userInfo: {},
    banners: [
      {
        "pic_url": "http://192.168.1.120:8080/business_images/wx_xcx/banner/1.png",
      },
      {
        "pic_url": "http://192.168.1.120:8080/business_images/wx_xcx/banner/2.png",
      },
      {
        "pic_url": "http://192.168.1.120:8080/business_images/wx_xcx/banner/3.png",
      }
    ]
  },
  onPullDownRefresh: function () {
    
  },
  //事件处理函数
  bindViewTap: function () {
    
  },
  onLoad: function () {
    
  },
  //当前位置选择
  chooseLocation: function () {
    var that = this
    wx.chooseLocation({
      success: function (res) {
        that.setData({
          localtion: res.name
        });
      }
    })
  }
})
