// pages/shop/list/settlement/settlement.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    popupFlag: "hide",
    payFlag:"show",
    popupContent: true,
    listpopup: [],
    listaddress:[],
    cartlist:[],
    cartTotal:0,
    address:null,
    time0:"立即送出",
    time1:"预计12：00送达",
    listtimes:[],
    dtype: "商家配送",
    paytype: "在线支付",
    cancels: "取消", 
    remarkstips: "口味/偏好等要求",
    remarks:"",
    defaults:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.loadCard();
    this.loadMemberAddress();
    //加载可以选择配送的时间
    this.loadSendTimeList();
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
  loadCard: function(){   //加载购物车的信息
    var cart = wx.getStorageSync(app.storageKey.cart);
    var total = 0;
    var count = 0;
    cart.forEach(function (obj, i) {
      total = total + (obj.count * obj.price);
      count = count + obj.count;
    });
    this.setData({
      cartTotal: total,
      cartCount: count,
      cartlist: cart
    });
    
  },
  loadMemberAddress: function(){  //加载地址
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
          key: app.storageKey.addressList,
          data: res.data.result.records
        });
        wx.setStorage({
          key: app.storageKey.addressCurrent,
          data: addr
        });
      }
    });
  },
  loadSendTimeList: function(){   //加载送货时间
    var that = this;
    wx.request({
      url: app.common.basePath + "/common/getSendTimeList", //
      success: function (res) {
        let time1 = res.data.result[0];
        that.setData({
          listtimes: res.data.result,
          time1: time1
        });
      }
    });
  },
  addrChange(){ //地址调整
    wx.setStorage({
      key: app.storageKey.addressListCatchtap,
      data: "addrSelect"
    });
    wx.navigateTo({
      url: '../../mine/address/address'
    });
  },
  timeChange(){ //配送时间
    this.setData({
      payFlag: "hide",
      popupFlag: "show",
      popupTitle: "请选择到达的时间",
      popuptype: "time",
      listpopup: this.data.listtimes
    });
  },
  popupSelect(e) {  //配送时间
    var val = e.currentTarget.dataset.val;
    var popuptype = e.currentTarget.dataset.popuptype;
    if (popuptype == "time"){
      this.setData({
        payFlag: "show",
        popupFlag: "hide",
        time0: "按时送出",
        time1: val 
      });
    } else if (popuptype == "dtype"){
      this.setData({
        payFlag: "show",
        popupFlag: "hide",
        dtype: val
      });
    }
    
  },
  dtypeChange(){  //配送方式
    var list = ["商家配送","门店自取"];
    this.setData({
      payFlag: "hide",
      popupFlag: "show",
      popupTitle: "请选择配送方式",
      popuptype: "dtype",
      listpopup: list
    });
  },
  payChange(){  //支付方式

  },
  dishChange(){ //餐具

  },
  remarksChange(){  //备注
    this.setData({
      payFlag: "hide",
      popupFlag: "show",
      popupContent: false,
      popupTitle: "备注/说明",
      cancels: "确定",
      popuptype: "remarks"
    });
  },
  textareablur(e) {
    let val = e.detail.value;
    let key = e.currentTarget.dataset.name;
    let vals = val;
    if(val.length>7){
      vals = val.substring(0,6)+"...";
    }
    this.setData({
      key: val,
      remarkstips: vals
    });

  },
  cancel(){
    this.setData({
      payFlag: "show",
      popupContent: true,
      popupFlag: "hide"
    });
  },
  pay(){
    var memberAddressId = this.data.address.id;
    var listDetail = this.data.cartlist;
    var time = this.data.time1;
    var dtype = this.data.dtype;
    var remarks = this.data.remarks;

    let data = {};
    data['listDetail'] = this.data.cartlist;
    data['memberAddressId'] = this.data.address.id;
    data['wxId'] = wx.getStorageSync('openid');
    data['remarks'] = this.data.remarks;
    data['deliveryDate'] = this.data.time1;
    data['deliveryType'] = this.data.dtype;
    data['payMethod'] = this.data.paytype;
    wx.request({
      url: app.common.basePath + "/order/create", //
      data: data,
      success: function (res) {
        wx.showToast({
          title: res.data.msg,
          duration: 1000
        });
        setTimeout(function () {
          var pages = getCurrentPages();
          var prevPage = pages[pages.length - 2];
          prevPage.data.listgoods.forEach(function (obj, i) {
            obj.count=0;
          });
          wx.setStorage({
            key: app.storageKey.cart,
            data: []
          });
          prevPage.setData({
            cartTotal: 0,
            cartCount: 0,
            listgoods: prevPage.data.listgoods,
            cart: []
          });
          wx.navigateBack();
        }, 1100);
      }
    });


  }

})