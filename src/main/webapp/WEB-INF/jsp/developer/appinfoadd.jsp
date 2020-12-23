<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>

<script type="text/javascript">
  $(function () {
    //给软件名称文本框绑定blur事件
    $("#softwareName").blur(function () {
      isEmpty("softwareName");
    })



    //给支持ROM文本框绑定blur事件
    $("#supportROM").blur(function () {
      isEmpty("supportROM");
    })

    //给界面语言文本框绑定blur事件
    $("#interfaceLanguage").blur(function () {
      isEmpty("interfaceLanguage");
    })

    //给软件大小文本框绑定blur事件
    $("#softwareSize").blur(function () {
      isEmpty("softwareSize");
    })

    //给下载次数文本框绑定blur事件
    $("#downloads").blur(function () {
      if ($.trim($("#downloads").val())==''){
        $("#downloadsSpan").css("color","red");
        $("#downloadsSpan").html("文本不能为空并都为数字")
      }else {
        $("#downloadsSpan").html("")
      }

    })

    //给应用简介文本框绑定blur事件
    $("#appInfo").blur(function () {
      isEmpty("appInfo");
    })


    //给保存按钮绑定单机事件
    $("#send").click(function () {
      //提交表单之前所有字段都进行验证是否全部填写完毕
      var softwareName = $.trim($("#softwareName").val());
      var APKName = $.trim($("#APKName").val());
      var supportROM = $.trim($("#supportROM").val());
      var interfaceLanguage = $.trim($("#interfaceLanguage").val());
      var softwareSize = $.trim($("#softwareSize").val());
      var downloads = $.trim($("#downloads").val());
      var flatformId = $.trim($("#flatformId").val());
      var categoryLevel1 = $.trim($("#categoryLevel1").val());
      var categoryLevel2 = $.trim($("#categoryLevel2").val());
      var categoryLevel3 = $.trim($("#categoryLevel3").val());
      var appInfo = $.trim($("#appInfo").val());
      var a_logoPicPath = $.trim($("#a_logoPicPath").val());
      var a_logoPicPathSpan = $("#a_logoPicPathSpan").html();

      if (softwareName!=''&&APKName!=''&&supportROM!=''&&interfaceLanguage!=''&&softwareSize!=''
              &&downloads!=''&&flatformId!=''&&categoryLevel1!=''&&categoryLevel2!=''
              &&categoryLevel3!=''&&appInfo!=''&&a_logoPicPath!=''&&a_logoPicPathSpan=="文件可用"){
        //全部填写了提交表单



        $("#appinfoaddForm").submit();
      }else {
        //没有全部填写则在相应位置进行提示
        $('#softwareName').trigger("blur");
        $('#APKName').trigger("blur");
        $('#supportROM').trigger("blur");
        $('#interfaceLanguage').trigger("blur");
        $('#softwareSize').trigger("blur");
        $('#downloads').trigger("blur");
        $('#appInfo').trigger("blur");

        if (flatformId==''){
          $("#flatformIdSpan").css("color","red");
          $("#flatformIdSpan").html("请选择所属平台！");
        }
        if (categoryLevel1==''){
          $("#categoryLevel1Span").css("color","red");
          $("#categoryLevel1Span").html("请选择一级分类！");
        }
        if (categoryLevel2==''){
          $("#categoryLevel2Span").css("color","red");
          $("#categoryLevel2Span").html("请选择二级分类！");
        }
        if (categoryLevel3==''){
          $("#categoryLevel3Span").css("color","red");
          $("#categoryLevel3Span").html("请选择三级分类！");
        }
        if (a_logoPicPath==''){
          $("#a_logoPicPathSpan").css("color","red");
          $("#a_logoPicPathSpan").html("请选择LOGO图片！");
        }
      }
    })
  })

  function isEmpty(textId) {
    if ($.trim($("#"+textId).val())==''){
      $("#"+textId+"Span").css("color","red");
      $("#"+textId+"Span").html("文本不能为空")
    }else {
      $("#"+textId+"Span").html("")
    }
  }



</script>
<div class="clearfix"></div>
<div class="row">
  <div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>新增APP基础信息 <i class="fa fa-user"></i><small>${devUserSession.devname}</small></h2>
             <div class="clearfix"></div>
      </div>
      <div class="x_content">
	  <!-- <div class="item form-group">
               <label class="control-label col-md-3 col-sm-3 col-xs-12" ></label>
               <div class="col-md-6 col-sm-6 col-xs-12">
                 <form action="uploadlogo" class="dropzone" style="height:100px;">
                 </form>
            <div class="clearfix"></div>
         </div>
       </div> -->
           <div class="clearfix"></div>
        <form class="form-horizontal form-label-left" action="appinfoaddsave" id="appinfoaddForm" method="post" enctype="multipart/form-data">
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">软件名称 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="softwareName" class="form-control col-md-7 col-xs-12" 
               data-validate-length-range="20" data-validate-words="1" name="softwarename"  required="required"
               placeholder="请输入软件名称" type="text"><span id="softwareNameSpan"></span>
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">APK名称 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="APKName" class="form-control col-md-7 col-xs-12" 
              	data-validate-length-range="20" data-validate-words="1" name="apkname"   required="required"
              	placeholder="请输入APK名称" type="text"><span id="APKNameSpan"></span>
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">支持ROM <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="supportROM" class="form-control col-md-7 col-xs-12" name="supportrom"
              	data-validate-length-range="20" data-validate-words="1"   required="required"
              	placeholder="请输入支持的ROM" type="text"><span id="supportROMSpan"></span>
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">界面语言 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="interfaceLanguage" class="form-control col-md-7 col-xs-12" 
              data-validate-length-range="20" data-validate-words="1" name="interfacelanguage"   required="required"
              placeholder="请输入软件支持的界面语言" type="text"><span id="interfaceLanguageSpan"></span>
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">软件大小 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="number" id="softwareSize" name="softwaresize"   required="required" onkeyup="value=value.replace(/[^\d]/g,'')"
              data-validate-minmax="10,500"  placeholder="请输入软件大小，单位为Mb" class="form-control col-md-7 col-xs-12"><span id="softwareSizeSpan"></span>
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">下载次数 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="number" id="downloads" name="downloads"   required="required"
              data-validate-minmax="10,500"  placeholder="请输入下载次数" class="form-control col-md-7 col-xs-12"><span id="downloadsSpan"></span>
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12"  for="select">所属平台 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <select name="flatformid" id="flatformId" class="form-control"   required="required"></select><span id="flatformIdSpan"></span>
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="select">一级分类 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <select name="categorylevel1" id="categoryLevel1" class="form-control"   required="required"> </select></select><span id="categoryLevel1Span"></span>
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12"  for="select">二级分类 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <select name="categorylevel2" id="categoryLevel2" class="form-control"  required="required"></select><span id="categoryLevel2Span"></span>
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="select">三级分类 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <select name="categorylevel3" id="categoryLevel3" class="form-control"  required="required"></select><span id="categoryLevel3Span"></span>
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">APP状态 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
            	<input type="hidden" name="status" id="status" value="1">待审核
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">应用简介 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <textarea id="appInfo" name="appinfo"     required="required"
              placeholder="请输入本软件的相关信息，本信息作为软件的详细信息进行软件的介绍。" class="form-control col-md-7 col-xs-12"></textarea><span id="appInfoSpan"></span>
            </div>
          </div>
           <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">LOGO图片 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="file" class="form-control col-md-7 col-xs-12" name="a_logoPicPath"  required="required" id="a_logoPicPath"/><span id="a_logoPicPathSpan"></span>
            ${fileUploadError }
            </div>
          </div>
          <div class="ln_solid"></div>
          <div class="form-group">
            <div class="col-md-6 col-md-offset-3">
              <button id="send" type="button" class="btn btn-success">保存</button>
              <button type="button" class="btn btn-primary" id="back">返回</button>
              <br/><br/>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<%@include file="common/footer.jsp"%>
<script src="statics/localjs/appinfoadd.js"></script>