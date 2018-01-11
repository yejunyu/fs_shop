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
  onLoad: function () {
    var url = app.common.basePath + "/order/list?pageNum=-1";
    var d = { 'pageNum': '-1', 'createBy': wx.getStorageSync('userid')};
    var that = this;
    wx.request({
      url: url, //
      data:d,
      success: function (res) {
        var list = res.data.result.records;
        list.forEach(function (obj, i) {
          if (obj.extraData.details.length > 20) {
            obj.extraData.details = obj.extraData.details.substring(0,20)+'...';
          }
          if (obj.status == 0) {
            obj.extraData.opera="取消";
          } else if (obj.status == 2) {
            obj.extraData.opera = "收货";
          } else if (obj.status == 3) {
            obj.extraData.opera = "评价";
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
  
  },
  orderdetail(e){
    var d = e.currentTarget.dataset.order;
    wx.setStorage({
      key: "key_mine_order_detail",
      data: d
    });
    wx.navigateTo({
      url: 'detail/detail'
    });
  },
  opera: function(e){
    var order = e.currentTarget.dataset.order;
    var status = 0;
    if (order.status==0){
      status = 6;
    }
    var url = app.common.basePath + "/order/editStatusById";
    var d = { 'status': status, 'id': order.id };
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