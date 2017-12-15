// pages/shop/cart/list.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    cart: wx.getStorageSync('key_cart_cache_list'),
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
  cartDetail() {
    if (this.data.cartDetailFlag == "hide") {
      this.setData({
        cartDetailFlag: "show"
      });
    } else {
      this.setData({
        cartDetailFlag: "hide"
      });
    }
  },
  cartClear() {
    this.cartDetail();
    this.data.listgoods.forEach(function (obj, i) {
      obj.count = 0;
    });
    this.setData({
      cartTotal: 0,
      cartCount: 0,
      listgoods: this.data.listgoods,
      cart: []
    });
  },
  switchSlider: function (e) {
    var index = e.target.dataset.index;
    this.setData({
      current: e.target.dataset.index
    });
    if (index == 0) {
      this.setData({
        listgoods: this.data.oldlistgoods
      });
    } else if (index == 1) {
      this.sortByPrice();
    }
  },
  sortByPrice(flag) {
    let list = this.data.listgoods;
    let current = this.data.current;
    var flag = true;
    if (this.data.sortPriceFlag) {
      flag = false;
      for (let i = 0; i < list.length; ++i) {
        for (let j = 0; j < list.length; ++j) {
          if ((list[i].price * 1) < (list[j].price * 1)) {
            let goods = list[i];
            list[i] = list[j];
            list[j] = goods;
          }
        }
      }
    } else {
      flag = true;
      for (let i = 0; i < list.length; ++i) {
        for (let j = 0; j < list.length; ++j) {
          if ((list[i].price * 1) > (list[j].price * 1)) {
            let goods = list[i];
            list[i] = list[j];
            list[j] = goods;
          }
        }
      }
    }
    this.setData({
      sortPriceFlag: flag,
      listgoods: list
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
    wx.setStorage({
      key: "key_shop_cart",
      data: this.data.cart
    });
    wx.setStorage({
      key: "key_shop_cart_total",
      data: this.data.cartTotal
    });
    wx.setStorage({
      key: "key_address_list",
      data: "addrSelect"
    });
    wx.navigateTo({
      url: 'settle/settlement'
    });
  }
})