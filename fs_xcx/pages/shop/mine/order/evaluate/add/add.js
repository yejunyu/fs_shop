
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    order: {},
    list: [],
    scoreImgs: [
      "../../../../../../images/icons/score_01.png",
      "../../../../../../images/icons/score_01.png",
      "../../../../../../images/icons/score_01.png",
      "../../../../../../images/icons/score_01.png",
      "../../../../../../images/icons/score_01.png"
    ],
    scores:{},
    evaluates:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var order = wx.getStorageSync(app.storageKey.orderCurrent);
    
    this.setData({
      order: order,
      list: order.listOrderDetail
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
  scoreclick: function (e){
    var val = e.currentTarget.dataset.val;
    var goodsId = e.currentTarget.dataset.goodsid;
    var list = this.data.scoreImgs;
    for (var i = 0; i < list.length;i++){
      if (i <= val) {
        list[i] = "../../../../../../images/icons/score_02.png";
      }else{
        list[i] = "../../../../../../images/icons/score_01.png";
      }
    }
    this.data.scores[goodsId] = val+1;
    this.setData({
      scoreImgs: list,
      currentImg:val,
      scores: this.data.scores
    });
  },
  etxtVal: function(e){
    var goodsId = e.currentTarget.dataset.goodsid;
    var val = e.detail.value;
    this.data.evaluates[goodsId] = val;
    this.setData({
      evaluates: this.data.evaluates
    });
  },
  save: function(){
    var list = this.data.list;
    var listData = [];
    for (var i = 0; i < list.length; i++) {
      var goodsId = list[i].goods.id;

      var evaluate={};
      evaluate['goodsId'] = goodsId;
      evaluate['orderId'] = this.data.order.id;
      evaluate['content'] = this.data.evaluates[goodsId];
      evaluate['goodsScore'] = this.data.scores[goodsId];

      listData.push(evaluate);
    }
    var data = {};
    data['list'] = listData;
    wx.request({
      url: app.common.basePath + "/orderEvaluate/create", //
      data: data,
      success: function (res) {
        wx.showToast({
          title: res.data.msg,
          duration: 1000
        });
        setTimeout(function () {
          // var pages = getCurrentPages();
          // var prevPage = pages[pages.length - 2];
          // prevPage.data.listgoods.forEach(function (obj, i) {
          //   obj.count = 0;
          // });
          // wx.setStorage({
          //   key: app.storageKey.cart,
          //   data: []
          // });
          // prevPage.setData({
          //   cartTotal: 0,
          //   cartCount: 0,
          //   listgoods: prevPage.data.listgoods,
          //   cart: []
          // });
          wx.navigateBack();
        }, 1100);
      }
    });
  }
})