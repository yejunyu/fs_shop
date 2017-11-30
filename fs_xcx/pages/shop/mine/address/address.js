// pages/shop/mine/address/address.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      listaddress: wx.getStorageSync('key_shop_listaddress'),
      address: wx.getStorageSync('key_shop_address'),
      tapval: wx.getStorageSync('key_address_list')
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
  addrSelect(e){
    let address = e.currentTarget.dataset.address;
    this.setData({
      listaddress: this.data.listaddress,
      address: address
    });
    wx.setStorage({
      key: "key_shop_address",
      data: address
    });

    //更新上个页面的数据
    var pages = getCurrentPages();
    var prevPage = pages[pages.length - 2];  //上一个页面
    //直接调用上一个页面的setData()方法，把数据存到上一个页面中去
    prevPage.setData({
      listaddress: this.data.listaddress,
      address: address
    });
    wx.setStorage({
      key: "key_shop_address",
      data: address
    });
    wx.navigateBack();
  },
  addrEdit(e){
    let address = e.currentTarget.dataset.address;
    wx.setStorage({
      key: "key_shop_address",
      data: address
    });
    wx.navigateTo({
      url: '../../mine/address/address/edit'
    });
  },
  addrCreate(e) {
    wx.navigateTo({
      url: '../../mine/address/address/edit'
    });
  }
})