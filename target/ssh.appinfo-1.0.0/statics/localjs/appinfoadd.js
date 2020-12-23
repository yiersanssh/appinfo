$(function(){  
	//动态加载所属平台列表
	$.ajax({
		type:"GET",//请求类型
		url:"datadictionarylist",//请求的url
		data:{tcode:"APP_FLATFORM"},//请求参数
		dataType:"json",//ajax接口（请求url）返回的数据类型
		success:function(data){//data：返回数据（json对象）
			$("#flatformId").html("");
			var options = "<option value=\"\">--请选择--</option>";
			for(var i = 0; i < data.length; i++){
				options += "<option value=\""+data[i].valueid+"\">"+data[i].valuename+"</option>";
			}
			$("#flatformId").html(options);
		},
		error:function(data){//当访问时候，404，500 等非200的错误状态码
			alert("加载平台列表失败！");
		}
	});  
	//动态加载一级分类列表
	$.ajax({
		type:"GET",//请求类型
		url:"categoryLevelList",//请求的url
		data:{pid:null},//请求参数
		dataType:"json",//ajax接口（请求url）返回的数据类型
		success:function(data){//data：返回数据（json对象）
			$("#categoryLevel1").html("");
			var options = "<option value=\"\">--请选择--</option>";
			for(var i = 0; i < data.length; i++){
				options += "<option value=\""+data[i].id+"\">"+data[i].categoryname+"</option>";
			}
			$("#categoryLevel1").html(options);
		},
		error:function(data){//当访问时候，404，500 等非200的错误状态码
			alert("加载一级分类列表失败！");
		}
	});  
	//动态加载二级分类列表
	$("#categoryLevel1").change(function(){
		var categoryLevel1 = $("#categoryLevel1").val();
		if(categoryLevel1 != '' && categoryLevel1 != null){
			$.ajax({
				type:"GET",//请求类型
				url:"categoryLevelList",//请求的url
				data:{pid:categoryLevel1},//请求参数
				dataType:"json",//ajax接口（请求url）返回的数据类型
				success:function(data){//data：返回数据（json对象）
					$("#categoryLevel2").html("");
					var options = "<option value=\"\">--请选择--</option>";
					for(var i = 0; i < data.length; i++){
						options += "<option value=\""+data[i].id+"\">"+data[i].categoryname+"</option>";
					}
					$("#categoryLevel2").html(options);
				},
				error:function(data){//当访问时候，404，500 等非200的错误状态码
					alert("加载二级分类失败！");
				}
			});
		}else{
			$("#categoryLevel2").html("");
			var options = "<option value=\"\">--请选择--</option>";
			$("#categoryLevel2").html(options);
		}
		$("#categoryLevel3").html("");
		var options = "<option value=\"\">--请选择--</option>";
		$("#categoryLevel3").html(options);
	});
	//动态加载三级分类列表
	$("#categoryLevel2").change(function(){
		var categoryLevel2 = $("#categoryLevel2").val();
		if(categoryLevel2 != '' && categoryLevel2 != null){
			$.ajax({
				type:"GET",//请求类型
				url:"categorylevellist.json",//请求的url
				data:{pid:categoryLevel2},//请求参数
				dataType:"json",//ajax接口（请求url）返回的数据类型
				success:function(data){//data：返回数据（json对象）
					$("#categoryLevel3").html("");
					var options = "<option value=\"\">--请选择--</option>";
					for(var i = 0; i < data.length; i++){
						options += "<option value=\""+data[i].id+"\">"+data[i].categoryname+"</option>";
					}
					$("#categoryLevel3").html(options);
				},
				error:function(data){//当访问时候，404，500 等非200的错误状态码
					alert("加载三级分类失败！");
				}
			});
		}else{
			$("#categoryLevel3").html("");
			var options = "<option value=\"\">--请选择--</option>";
			$("#categoryLevel3").html(options);
		}
	});
	
	$("#back").on("click",function(){
		window.location.href = "dev/flatform/app/list";
	});
	
	$("#APKName").bind("blur",function(){
		//ajax后台验证--APKName是否已存在
		$.ajax({
			type:"GET",//请求类型
			url:"apkexist",//请求的url
			data:{APKName:$("#APKName").val()},//请求参数
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
				if(data.APKName == "empty"){//参数APKName为空，错误提示
					$("#APKNameSpan").css("color","red");
					$("#APKNameSpan").html("APKName不能为空")
				}else if(data.APKName == "exist"){//账号不可用，错误提示
					$("#APKNameSpan").css("color","red");
					$("#APKNameSpan").html("该APKName已存在，不能使用！")
				}else if(data.APKName == "noexist"){//账号可用，正确提示
					$("#APKNameSpan").css("color","green");
					$("#APKNameSpan").html("该APKName可以使用！")
				}
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				alert("请求错误！");
			}
		});
	});

	$("#a_logoPicPath").change(function () {

		$("#a_logoPicPathSpan").html("")

		//获取上传的文件
		var file = $("#a_logoPicPath")[0]
		//file控件有个files属性，该属性是一个数组。数组中的元素有以下属性：lastModifiedDate，name，size，type，webkitRelativePath，

		//获取文件的后缀名
		var suffix = file.files[0].name.substring(file.files[0].name.lastIndexOf("."));


		var maxSize = 50*1024;
		if (file.files[0].size>50*1024){
			$("#a_logoPicPathSpan").css("color","red")
			$("#a_logoPicPathSpan").html("文件过大")
		}else {
			if (".jpg"!=suffix&&".jpeg"!=suffix&&".png"!=suffix){
				$("#a_logoPicPathSpan").css("color","red")
				$("#a_logoPicPathSpan").html("文件格式不正确")
			}else {
				$("#a_logoPicPathSpan").css("color","green")
				$("#a_logoPicPathSpan").html("文件可用")

				//alert($("#a_logoPicPathSpan").css("color"))
			}
		}



		/*$.ajax({
			url:"picInfo",
			data:{
				a_logoPicPath:$("#a_logoPicPath").val()
			},
			type:"get",
			dataType:"json",
			success:function (data) {
				/!*
					data
					{ok:true/false
						msg:图片太大/图片格式必须为jpg,}
				*!/
				if (data.ok){
					$("#a_logoPicPathSpan").css("color","green");
					$("#a_logoPicPathSpan").html("图片可用")
				}else {
					$("#a_logoPicPathSpan").css("color","red");
					$("#a_logoPicPathSpan").html(data.msg);
				}

			}
		})*/
	})

});
      
      
      