// pages/shop/agency/add/add.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    agency:{},
    total:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var agency = wx.getStorageSync(app.storageKey.agencyCurrent);
    if (agency){
      this.setData({
        agency: agency
      });
      this.totals();
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
    var agency = this.data.agency;
    agency[key] = val;
    this.setData({
      agency: agency
    });
    this.totals(key);   //统计金额
  },
  totals:function(key){
    var basicCost = this.data.agency['basicCost'];
    var serviceCost = this.data.agency['serviceCost'];
    if (!basicCost) {
      basicCost = 0;
    } else {
      basicCost = basicCost * 1;
    }
    if (!serviceCost) {
      serviceCost = 0;
    } else {
      serviceCost = serviceCost * 1;
    }
    this.setData({
      total: (basicCost + serviceCost)
    });
  },
  save:function(){
    var data = this.data.agency;
    data['type']=0;
    var url = "";
    if(data.id){
      url = app.common.basePath + "/agency/update";
    }else{
      url = app.common.basePath + "/agency/create";
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
          lista.forEach(function (obj, i) {
            if (obj.id == data.id){
              lista[i] = data;
            }
          });
          prevPage.setData({
            list: lista
          });
          wx.navigateBack();
        }, 1100);
      }
    });
  }
})