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


    //给logo图片绑定change事件
    $("#attach").change(function () {
      //验证图片是否符合规定

      $("#attachSpan").html("")

      //获取上传的文件
      var file = $("#attach")[0]
      //file控件有个files属性，该属性是一个数组。数组中的元素有以下属性：lastModifiedDate，name，size，type，webkitRelativePath，

      //获取文件的后缀名
      var suffix = file.files[0].name.substring(file.files[0].name.lastIndexOf("."));


      var maxSize = 1024*500;
      if (file.files[0].size>maxSize){
        $("#attachSpan").css("color","red")
        $("#attachSpan").html("文件过大")
      }else {
        if (".jpg"!=suffix&&".jpeg"!=suffix&&".png"!=suffix&&".pneg"!=suffix){
          $("#attachSpan").css("color","red")
          $("#attachSpan").html("文件格式不正确")
        }else {
          $("#attachSpan").css("color","green")
          $("#attachSpan").html("文件可用")

          //alert($("#a_logoPicPathSpan").css("color"))
        }
      }



    })


    //给保存按钮绑定单机事件
    $("#send1").click(function () {
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
      var attach = $.trim($("#attach").val());
      var attachSpan = $("#attachSpan").html();


      if (softwareName!=''&&APKName!=''&&supportROM!=''&&interfaceLanguage!=''&&softwareSize!=''
              &&downloads!=''&&flatformId!=''&&categoryLevel1!=''&&categoryLevel2!=''
              &&categoryLevel3!=''&&appInfo!=''){
        //全部填写了则判断$("#uploadfile")是否显示
        if ($("#uploadfile").css("display")=="none"){
          //如果不显示则可以提交表单

          $("#appinfomodifyForm").submit();
        }else {
          //如果显示则判断file选择框是否为空
          if (attach!=''&&attachSpan=='文件可用'){
            //如果不为空则可以提交表单
           $("#appinfomodifyForm").submit();
          }else {
            //如果为空则添加提示信息
              $("#attachSpan").css("color","red");
              $("#attachSpan").html("请选择LOGO图片！");

          }
        }


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

      }
    })

    //给保存并审核按钮绑定单机事件
    $("#send2").click(function () {
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
      var attach = $.trim($("#attach").val());
      var attachSpan = $("#attachSpan").html();


      if (softwareName!=''&&APKName!=''&&supportROM!=''&&interfaceLanguage!=''&&softwareSize!=''
              &&downloads!=''&&flatformId!=''&&categoryLevel1!=''&&categoryLevel2!=''
              &&categoryLevel3!=''&&appInfo!=''){
        //全部填写了则判断$("#uploadfile")是否显示
        if ($("#uploadfile").css("display")=="none"){
          //如果不显示则可以提交表单

          $("#appinfomodifyForm").submit();
        }else {
          //如果显示则判断file选择框是否为空
          if (attach!=''&&attachSpan=='文件可用'){
            //如果不为空则可以提交表单
            $("#appinfomodifyForm").submit();
          }else {
            //如果为空则添加提示信息
            $("#attachSpan").css("color","red");
            $("#attachSpan").html("请选择LOGO图片！");

          }
        }


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
        <h2>修改APP基础信息 <i class="fa fa-user"></i><small>${devUserSession.devname}</small></h2>
             <div class="clearfix"></div>
      </div>
      <div class="x_content">
        <form class="form-horizontal form-label-left" id="appinfomodifyForm" action="appinfomodifysave" method="post" enctype="multipart/form-data">
          <input type="hidden" name="id" id="id" value="${appInfo.id}">
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">软件名称 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="softwareName" class="form-control col-md-7 col-xs-12" 
               data-validate-length-range="20" data-validate-words="1" 
               name="softwarename" value="${appInfo.softwarename}" required="required"
               placeholder="请输入软件名称" type="text"><span id="softwareNameSpan"></span>
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">APK名称 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="APKName" type="text" class="form-control col-md-7 col-xs-12" 
              name="apkname" value="${appInfo.apkname}" readonly="readonly">
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">支持ROM <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="supportROM" class="form-control col-md-7 col-xs-12" 
              	name="supportrom" value="${appInfo.supportrom}" required="required"
              	data-validate-length-range="20" data-validate-words="1" 
              	placeholder="请输入支持的ROM" type="text"><span id="supportROMSpan"></span>
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">界面语言 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="interfaceLanguage" class="form-control col-md-7 col-xs-12" 
              data-validate-length-range="20" data-validate-words="1"  required="required"
              name="interfacelanguage" value="${appInfo.interfacelanguage}"
              placeholder="请输入软件支持的界面语言" type="text"><span id="interfaceLanguageSpan"></span>
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">软件大小 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="number" id="softwareSize" name="softwaresize" value="${appInfo.softwaresize}" required="required"
              data-validate-minmax="10,500"  placeholder="请输入软件大小，单位为Mb" class="form-control col-md-7 col-xs-12"><span id="softwareSizeSpan"></span>
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">下载次数 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="number" id="downloads" name="downloads" value="${appInfo.downloads}" required="required"
              data-validate-minmax="10,500"  placeholder="请输入下载次数" class="form-control col-md-7 col-xs-12"><span id="downloadsSpan"></span>
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12"  for="select">所属平台 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="hidden" value="${appInfo.flatformid}" id="fid" />
              <select name="flatformid" id="flatformId" class="form-control" required="required"></select><span id="flatformIdSpan"></span>
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="select">一级分类 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="hidden" value="${appInfo.categorylevel1}" id="cl1" />
              <select name="categorylevel1" id="categoryLevel1" class="form-control"  required="required"></select><span id="categoryLevel1Span"></span>
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12"  for="select">二级分类 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
            	<input type="hidden" value="${appInfo.categorylevel2}" id="cl2" />
              <select name="categorylevel2" id="categoryLevel2" class="form-control"  required="required"></select><span id="categoryLevel2Span"></span>
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="select">三级分类 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="hidden" value="${appInfo.categorylevel3}" id="cl3" />
              <select name="categorylevel3" id="categoryLevel3" class="form-control"  required="required"></select><span id="categoryLevel3Span"></span>
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">APP状态 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
            	<input id="statusName" type="text" class="form-control col-md-7 col-xs-12" 
              	name="statusname" value="${appInfo.statusname}" readonly="readonly">
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">应用简介 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <textarea id="appInfo" name="appinfo" required="required"
              placeholder="请输入本软件的相关信息，本信息作为软件的详细信息进行软件的介绍。" class="form-control col-md-7 col-xs-12">
              ${appInfo.appinfo}</textarea><span id="appInfoSpan"></span>
            </div>
          </div>
           <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">LOGO图片 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
				<input type="hidden" id="logoPicPath" name="logoPicPath" value="${appInfo.logopicpath}"/>
            	<input type="hidden" id="logoLocPath" name="logoLocPath" value="${appInfo.logolocpath}"/>
				<div id="uploadfile" style="display: none">
				<input id="attach"  type="file" class="form-control col-md-7 col-xs-12" name="attach"><span id="attachSpan"></span>
				<p><span style="color:red;font-weight: bold;">*注：1、大小不得超过500k.2、图片格式：jpg、png、jpeg、pneg</span></p>
				</div>
				<div id="logoFile"></div>
				${fileUploadError }
            </div>
          </div>
          <div class="form-group">
            <div class="col-md-6 col-md-offset-3">
            	<c:if test="${appInfo.status == 3}">
            	 	<button id="send2" type="button" name="status" value="1" class="btn btn-success">保存并再次提交审核</button>
            	</c:if>
              <button id="send1" type="button" class="btn btn-success">保存</button>
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
<script src="statics/localjs/appinfomodify.js"></script>