var app = getApp()
Page({
  data: {
    hidden: false,
    current:0,
    cart: [],
    cartTotal: 0,
    cartCount: 0,
    cartDetailFlag: "hide",
    temps:[],
    listgoods: []
  },
  onPullDownRefresh: function () {
    console.log('onPullDownRefresh')
  },
  onLoad: function (options) {
    var tempId = wx.getStorageSync(app.storageKey.goodsTempId);
    this.loadGoodsList(tempId);
    this.loadGoodsTemp(tempId);
    this.setData({
      parentTempId: tempId,
      current: tempId
    });
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
  },
  onHide: function () {
    // 页面隐藏
    this.storageCart();
  },
  onUnload: function () {
    // 页面关闭
    this.storageCart();
  },
  storageCart: function(){
    var d = this.data.cart;
    wx.setStorage({
      key: app.storageKey.cart,
      data: this.data.cart
    });
  },
  loadGoodsList: function (tempId){    //加载商品列表
    var url = app.common.basePath + "/goods/list";
    var data = { pageNum: '-1', tempId: tempId };
    var that = this;
    wx.request({
      url: url, //仅为示例，并非真实的接口地址
      data: data,
      success: function (res) {
        //还原已经加在购物车中的数据
        var cart = wx.getStorageSync(app.storageKey.cart);
        if(cart){
          cart.forEach(function (obj, i) {
            res.data.result.forEach(function (g, j) {
              if (obj.id == g.id) {
                g.count = obj.count;
              }
            });
          });
          that.setData({
            listgoods: res.data.result,
            cart: cart
          });
        }else{
          that.setData({
            listgoods: res.data.result
          });
        }
        that.calculation();
      }
    });
  },
  loadGoodsTemp: function (tempId){  //加载商品类型数据
    var url = app.common.basePath + "/goodsTemp/list";
    var data = { pageNum: '-1', parentId: tempId };
    var that = this;
    wx.request({
      url: url, //仅为示例，并非真实的接口地址
      data: data,
      success: function (res) {
        that.setData({
          temps: res.data.result.records
        });
      }
    });
  },
  cartAdd(event){ //添加到购物车
    let goods = event.currentTarget.dataset.goods;
    let goodsId = goods.id;
    this.data.listgoods.forEach(function (obj, i) {
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
  cartSubtract(event) { //移出购物车
    let goods = event.currentTarget.dataset.goods;
    let goodsId = goods.id;
    this.data.listgoods.forEach(function (obj, i) {
      if (obj.id == goodsId) {
        if (obj.count && obj.count > 0) {
          obj.count -= 1;
        } else {
          obj.count = 0;
        }
      }
    });
    this.calculation();
  },
  calculation(){  //计算总量以及总价
    let total = 0;
    let count = 0;
    let cart = wx.getStorageSync(app.storageKey.cart);
    if(!cart){
      cart = [];
    }
    this.data.listgoods.forEach(function (obj, i) {
      if (obj.count && obj.count > 0) {
        let goodsFlag = true;
        cart.forEach(function (c, j) {
          if(c.id==obj.id){
            c.count = obj.count;
            goodsFlag = false;
          }
        });
        if(goodsFlag){
          cart.push(obj);
        }
      }
    });
    cart.forEach(function (obj, i) {
      total = total + (obj.count * obj.price);
      count = count + obj.count;
    });
    this.setData({
      cartTotal: total,
      cartCount: count,
      cart:cart,
      listgoods: this.data.listgoods
    });
  },
  cartDetail(){   //购物车详情
    if (this.data.cartDetailFlag=="hide"){
      this.setData({
        cartDetailFlag: "show"
      });
    }else{
      this.setData({
        cartDetailFlag: "hide"
      });
    }
  },
  cartClear(){    //清空购物车
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
    wx.setStorage({
      key: app.storageKey.cart,
      data: []
    });
  },
  settlement(){   //结算
    if(this.data.cart.length==0){
      wx.showToast({
        title: "购物车不能为空",
        duration: 2000
      });
      return;
    }
    wx.navigateTo({
      url: 'settle/settlement'
    });
  },
  tempChoose(event) {  //商品类型选择
    var tempId = event.target.dataset.id;
    this.setData({
      current: tempId
    });
    this.storageCart();
    this.loadGoodsList(tempId);
  }
})
