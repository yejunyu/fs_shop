//获取应用实例
var app = getApp()

Page({
  data: {
    localtion: '广州市政府',
    interval: 3000,
    duration: 1000,


    motto: 'MiHome_Store',
    userInfo: {},
    menus:[],
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
    var url = app.common.basePath + "/goodsTemp/list";
    var that = this;
    wx.request({
      url: url, //仅为示例，并非真实的接口地址
      data: {
        pageNum: '-1',
        parentId:"-1"
      },
      success: function (res) {
        var list = res.data.result.records;
        var res = [];
        var objs = [];
        var idx = 1;
        for(var i=0;i<list.length;i++){
          objs.push(list[i]);
          if (idx==4){
            res.push(objs);
            idx = 1;
            objs = [];
          }else{
            idx = idx+1;
          }
        }
        if (idx>1){
          res.push(objs);
        }
        that.setData({
          menus: res
        });
      }
    });
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
  },
  clickMenu(event){
    var tempId = event.currentTarget.dataset.id;
    console.log("tempId:" + tempId);
    wx.setStorage({
      key: "key_goods_goodsTemp_id",
      data: tempId
    });
    wx.navigateTo({
      url: '../goods/list'
    });
    
  }
})
