// pages/shop/sys/feedback/add/add.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    feedback:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var feedback = wx.getStorageSync(app.storageKey.feedbackCurrent);
    if (feedback) {
      this.setData({
        feedback: feedback
      });
    }
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
  inputkeyin(e) {
    var val = e.detail.value;
    var key = e.currentTarget.dataset.name;
    var feedback = this.data.feedback;
    feedback[key] = val;
    this.setData({
      feedback: feedback
    });
  },
  save: function () {
    var data = this.data.feedback;
    var url = "";
    if (data.id) {
      url = app.common.basePath + "/feedback/update";
    } else {
      url = app.common.basePath + "/feedback/create";
    }
    wx.request({
      url: url, //
      data: app.paramsFilter(data),
      success: function (res) {
        wx.showToast({
          title: res.data.msg,
          duration: 1000
        });
        setTimeout(function () {
          var pages = getCurrentPages();
          var prevPage = pages[pages.length - 2];
          var lista = prevPage.data.list;
          if(lista){
            lista.forEach(function (obj, i) {
              if (obj.id == data.id) {
                lista[i] = data;
              }
            });
            prevPage.setData({
              list: lista
            });
          }
          wx.navigateBack();
        }, 1100);
      }
    });
  }
})