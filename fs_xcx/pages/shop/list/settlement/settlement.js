// pages/shop/list/settlement/settlement.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    popupFlag: "hide",
    payFlag:"show",
    listpopup: [],
    listaddress:[],
    address:null,
    time0:"立即送出",
    time1:"预计12：00送达",
    dtype: "商家配送",
    paytype: "在线支付",
    defaults:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var url = app.common.basePath + "/memberAddress/list";
    var that = this;
    wx.request({
      url: url, //
      data: {
        pageNum: '-1'
      },
      success: function (res) {
        let addr = res.data.result.records[0];
        that.setData({
          listaddress: res.data.result.records,
          address: addr
        });
        wx.setStorage({
          key: "key_shop_listaddress",
          data: res.data.result.records
        });
        wx.setStorage({
          key: "key_shop_address",
          data: addr
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
  addrChange(){
    wx.navigateTo({
      url: '../../mine/address/address'
    });
  },
  timeChange(){

  },
  dtypeChange(){

  },
  payChange(){

  },
  dishChange(){

  },
  remarksChange(){

  },
  cancel(){
    that.setData({
      popupFlag: "hide"
    });
  }

})