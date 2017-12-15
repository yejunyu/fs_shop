// pages/shop/cart/list.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    cart: [],
    cartTotal: 0,
    cartCount: 0
  
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
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
    console.log("生命周期函数--监听页面显示");
    var cart = wx.getStorageSync(app.storageKey.cart);
    if (cart) {
      var total = 0;
      var count = 0;
      cart.forEach(function (obj, i) {
        total = total + (obj.count * obj.price);
        count = count + obj.count;
      });
      this.setData({
        cartTotal: total,
        cartCount: count,
        cart: cart
      });
    }
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
  cartClear() {
    this.setData({
      cartTotal: 0,
      cartCount: 0,
      cart: []
    });
    wx.setStorage({
      key: app.storageKey.cart,
      data: []
    });
  },
  cartAdd(event){ //数量增加
    let goods = event.currentTarget.dataset.goods;
    let goodsId = goods.id;
    this.data.cart.forEach(function (obj, i) {
      if (obj.id == goodsId) {
        if (obj.count) {
          obj.count += 1;
        } else {
          obj.count = 1;
        }
      }
    });
    this.calculation();
  },
  cartSubtract(event) {   //数量减少
    let goods = event.currentTarget.dataset.goods;
    let goodsId = goods.id;
    var cart = this.data.cart;
    var that = this;
    this.data.cart.forEach(function (obj, i) {
      if (obj.id == goodsId) {
        if (obj.count && obj.count > 0) {
          obj.count -= 1;
          if (obj.count==0){
            cart.splice(i,1);
            that.setData({
              cart: cart
            });
          }
        }
      }
    });
    this.calculation();
  },
  calculation(){  //计算总量以及总价
    let total = 0;
    let count = 0;
    this.data.cart.forEach(function (obj, i) {
      total = total + (obj.count * obj.price);
      count = count + obj.count;
    });
    this.setData({
      cartTotal: total,
      cartCount: count,
      cart: this.data.cart
    });
    wx.setStorage({
      key: app.storageKey.cart,
      data: this.data.cart
    });
  },
  settlement() {
    if (this.data.cart.length == 0) {
      wx.showToast({
        title: "购物车不能为空",
        duration: 2000
      });
      return;
    }
    wx.navigateTo({
      url: '../goods/settle/settlement'
    });
  }
})