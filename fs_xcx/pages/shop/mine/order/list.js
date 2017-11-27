// pages/shop/mine/order/list.js
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
    var url = app.common.basePath + "/order/list?pageNum=-1";
    var d = { 'pageNum': '-1', 'createBy': wx.getStorageSync('userid')};
    var that = this;
    wx.request({
      url: url, //
      data:d,
      success: function (res) {
        var list = res.data.result.records;
        list.forEach(function (obj, i) {
          if (obj.extraData.details.length > 15) {
            obj.extraData.details = obj.extraData.details.substring(0,13)+'...';
          }
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
  
  }
})