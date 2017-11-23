// pages/shop/mine/address/address/edit.js
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    address:{},
    sexval:'先生'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var obj = wx.getStorageSync('key_shop_address');
    this.setData({
      address: obj,
      sexval:obj.sex
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
  chooseLocation: function () {
    var that = this
    wx.chooseLocation({
      success: function (res) {
        var obj = that.data.address;
        obj['remarks'] = res.address;
        obj['address'] = res.name;
        that.setData({
          address: obj
        });
      }
    })
  },
  inputblur(e){
    let val = e.detail.value;
    let key = e.currentTarget.dataset.name;
    this.changeObj(key, val);
  },
  sexclick(e) {
    let key = e.currentTarget.dataset.name;
    let val = e.currentTarget.dataset.value;
    this.changeObj(key, val);
    this.setData({
      sexval: val
    });
  },
  changeObj(key,val){
    var obj = this.data.address;
    obj[key] = val;
    this.setData({
      address: obj
    });
  },
  addrSave(){
    var url ;
    var address = this.data.address;
    if(!address.id){
      url = app.common.basePath + "/memberAddress/create";
    }else{
      url = app.common.basePath + "/memberAddress/update";
    }
    var that = this;
    wx.request({
      url: url, //仅为示例，并非真实的接口地址
      data: app.paramsFilter(address),
      success: function (res) {
        wx.showToast({
          title: res.data.msg,
          duration: 2000
        });
        setTimeout(function () {
          var pages = getCurrentPages();
          var prevPage = pages[pages.length - 2];
          var url = app.common.basePath + "/memberAddress/list";
          wx.request({
            url: url, //
            data: {
              pageNum: '-1'
            },
            success: function (res) {
              prevPage.setData({
                listaddress: res.data.result.records,
                address: address
              });
              wx.setStorage({
                key: "key_shop_address",
                data: address
              });
              wx.setStorage({
                key: "key_shop_listaddress",
                data: res.data.result.records
              });
              wx.navigateBack();
            }
          });
        }, 1500)
        
        
      }
    });
  }
})