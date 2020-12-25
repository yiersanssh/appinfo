function delfile(id){
	$.ajax({
		type:"GET",//请求类型
		url:"delfileversion",//请求的url
		data:{id:id,flag:'apk'},//请求参数
		dataType:"json",//ajax接口（请求url）返回的数据类型
		success:function(data){//data：返回数据（json对象）
			if(data.result == "success"){
				alert("删除成功！");
				$("#uploadfile").show();
				$("#apkFile").html('');
			}else if(data.result == "failed"){
				alert("删除失败！");
			}
		},
		error:function(data){//当访问时候，404，500 等非200的错误状态码
			alert("请求错误！");
		}
	});  
}

$(function(){  
	$("#back").on("click",function(){
		window.location.href = "dev/flatform/app/list";
	});
	
	//上传APK文件---------------------
	var downloadLink = $("#downloadLink").val();
	var id = $("#id").val();
	var apkFileName = $("#apkFileName").val();
	if(downloadLink == null || downloadLink == "" ){
		$("#uploadfile").show();
	}else{
		$("#apkFile").append("<p>"+apkFileName+
							"&nbsp;&nbsp;<a href=\""+downloadLink+"?m="+Math.random()+"\" >下载</a> &nbsp;&nbsp;" +
							"<a href=\"javascript:;\" onclick=\"delfile('"+id+"');\">删除</a></p>");
	}





//给版本大小文本框绑定blur事件

	//给版本简介文本框绑定blur事件
	$("#versionInfo").blur(function () {
		isEmpty("versionInfo");
	})

	//给版本大小文本框绑定blur事件
	$("#versionSize").blur(function () {
		if ($.trim($("#versionSize").val())==''){
			$("#versionSizeSpan").css("color","red");
			$("#versionSizeSpan").html("文本不能为空并都为数字")
		}else {
			$("#versionSizeSpan").html("")
		}
	})

	//apk文件绑定change事件
	$("#attach").change(function () {

		$("#attachSpan").html("")

		//获取上传的文件
		var file = $("#attach")[0]
		//file控件有个files属性，该属性是一个数组。数组中的元素有以下属性：lastModifiedDate，name，size，type，webkitRelativePath，

		//获取文件的后缀名
		var suffix = file.files[0].name.substring(file.files[0].name.lastIndexOf("."));


		var maxSize = 500*1024*1024;
		if (file.files[0].size>maxSize){
			$("#attachSpan").css("color","red")
			$("#attachSpan").html("文件过大，不得超过500mb")
		}else {
			if (".apk"!=suffix){
				$("#attachSpan").css("color","red")
				$("#attachSpan").html("文件格式不正确,必须为apk文件")
			}else {
				$("#attachSpan").css("color","green")
				$("#attachSpan").html("文件可用")

				//alert($("#a_logoPicPathSpan").css("color"))
			}
		}
	})




	//给保存按钮绑定单机事件
	$("#send").click(function () {

		//提交表单之前所有字段都进行验证是否全部填写完毕

		var versionInfo = $.trim($("#versionInfo").val());
		var versionSize = $.trim($("#versionSize").val());
		var attach = $("#attach").val();
		var attachSpan = $("#attachSpan").html();
		//alert(a_downloadLinkSpan)

		if (versionInfo!=''&&versionSize!=''){
			//全部填写了则判断文件上传框是否显示
			if($("#uploadfile").css("display")=="none"){
				//如果没有显示则可以提交
				$("#appversionmodifyForm").submit();
			}else{
				//显示了则需要判断是否有选择文件
				if (attach==''){
					$("#attachSpan").css("color","red");
					$("#attachSpan").html("请选择LOGO图片！");
				}else {
					//选择了则可	以提交
					$("#appversionmodifyForm").submit();
				}
			}

		}else {
			//没有全部填写则在相应位置进行提示
			$('#versionInfo').trigger("blur");
			$('#versionSize').trigger("blur");

			if (attach==''){
				$("#attachSpan").css("color","red");
				$("#attachSpan").html("请选择LOGO图片！");
			}
		}
	})





});

function isEmpty(textId) {
	if ($.trim($("#"+textId).val())==''){
		$("#"+textId+"Span").css("color","red");
		$("#"+textId+"Span").html("文本不能为空")
	}else {
		$("#"+textId+"Span").html("")
	}
}
      
      
      