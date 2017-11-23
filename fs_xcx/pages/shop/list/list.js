
var app = getApp()
Page({
  data: {
    hidden: false,
    current:0,
    cart: [],
    cartTotal: 0,
    cartCount: 0,
    cartDetailFlag: "hide",
    listgoods: []
  },
  onPullDownRefresh: function () {
    console.log('onPullDownRefresh')
  },
  onLoad: function (options) {
    var url = app.common.basePath + "/goods/list";
    var that = this;
    wx.request({
      url: url, //仅为示例，并非真实的接口地址
      data: {
        pageNum: '-1'
      },
      success: function (res) {
        that.setData({
          listgoods: res.data.result.records,
          oldlistgoods: res.data.result.records
        });
      }
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
  },
  onUnload: function () {
    // 页面关闭
  },

  //自定义事件
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
    let cart = [];
    let total = 0;
    let count = 0;
    this.data.listgoods.forEach(function (obj, i) {
      if (obj.count && obj.count > 0) {
        total = total+(obj.count*obj.price);
        count = count+obj.count;
        cart.push(obj);
      }
    });
    this.setData({
      cartTotal: total,
      cartCount: count,
      listgoods: this.data.listgoods,
      cart: cart
    });
  },
  cartDetail(){
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
  cartClear(){
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
    if(index==0){
      this.setData({
        listgoods: this.data.oldlistgoods
      });
    }else if (index==1){
      this.sortByPrice();
    }
  },
  sortByPrice(flag){
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
    }else{
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
  settlement(){
    wx.navigateTo({
      url: 'settlement/settlement'
    });
  }
  


})
