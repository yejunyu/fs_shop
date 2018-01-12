// pages/shop/agency/list.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var url = app.common.basePath + "/agency/list";
    var d = { 'pageNum': '-1'};
   // var d = { 'pageNum': '-1', 'createBy': wx.getStorageSync('userid') };
    var that = this;
    wx.request({
      url: url, //
      data: d,
      success: function (res) {
        var list = res.data.result.records;
        list.forEach(function (obj, i) {
          if (obj.content.length > 20) {
            obj.contentDis = obj.content.substring(0, 19) + '...';
          }else{
            obj.contentDis = obj.content;
          }
          if (obj.status == 0) {
            obj.extraData.opera = "取消";
          } else if (obj.status == 2) {
            obj.extraData.opera = "完成";
          } 
          // else if (obj.status == 3) {
          //   obj.extraData.opera = "评价";
          // }
        });
        that.setData({
          list: list
        });
      }
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },
  detail: function(e){
    var d = e.currentTarget.dataset.agency;
    wx.setStorage({
      key: app.storageKey.agencyCurrent,
      data: d
    });
    wx.navigateTo({
      url: '../agency/add/add'
    });
  },
  opera: function (e) {
    var agency = e.currentTarget.dataset.agency;
    var status = 0;
    if (agency.status == 0) {
      status = 6;
    } else if (agency.status == 2) {
      status = 3;
    } else {
      return;
    }
    var url = app.common.basePath + "/agency/update";
    var d = { 'status': status, 'id': agency.id };
    var that = this;
    wx.request({
      url: url, //
      data: d,
      success: function (res) {
        wx.showToast({
          title: res.data.msg,
          duration: 1500
        });
        that.onLoad();
      }
    });

  }
})