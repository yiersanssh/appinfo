$(function(){


	$("#back").on("click",function(){
		window.location.href = "dev/flatform/app/list";
	});


	//给版本号文本框绑定blur事件
	$("#versionNo").blur(function () {
		isEmpty("versionNo");
	})

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
	$("#a_downloadLink").change(function () {

		$("#a_downloadLinkSpan").html("")

		//获取上传的文件
		var file = $("#a_downloadLink")[0]
		//file控件有个files属性，该属性是一个数组。数组中的元素有以下属性：lastModifiedDate，name，size，type，webkitRelativePath，

		//获取文件的后缀名
		var suffix = file.files[0].name.substring(file.files[0].name.lastIndexOf("."));


		var maxSize = 500*1024*1024;
		if (file.files[0].size>maxSize){
			$("#a_downloadLinkSpan").css("color","red")
			$("#a_downloadLinkSpan").html("文件过大，不得超过500mb")
		}else {
			if (".apk"!=suffix){
				$("#a_downloadLinkSpan").css("color","red")
				$("#a_downloadLinkSpan").html("文件格式不正确,必须为apk文件")
			}else {
				$("#a_downloadLinkSpan").css("color","green")
				$("#a_downloadLinkSpan").html("文件可用")

				//alert($("#a_logoPicPathSpan").css("color"))
			}
		}
	})


	//给保存按钮绑定单机事件
	$("#send").click(function () {
		//提交表单之前所有字段都进行验证是否全部填写完毕

		var versionNo = $.trim($("#versionNo").val());
		var versionInfo = $.trim($("#versionInfo").val());
		var versionSize = $.trim($("#versionSize").val());
		var a_downloadLink = $("#a_downloadLink").val();
		var a_downloadLinkSpan = $("#a_downloadLinkSpan").html();
		//alert(a_downloadLinkSpan)
		if (versionNo!=''&&versionInfo!=''&&versionSize!=''&&a_downloadLinkSpan=="文件可用"){
			//全部填写了提交表单
			//alert(a_downloadLinkSpan=="文件可用")
			$("#addversionForm").submit();
		}else {
			//没有全部填写则在相应位置进行提示
			$('#versionNo').trigger("blur");
			$('#versionInfo').trigger("blur");
			$('#versionSize').trigger("blur");


			if (a_downloadLink==''){
				$("#a_downloadLinkSpan").css("color","red");
				$("#a_downloadLinkSpan").html("请选择LOGO图片！");
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