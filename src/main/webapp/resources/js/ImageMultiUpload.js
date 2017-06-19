function ImageMultiUpload(o)
{
	this.groupId = o._groupId;
	this.max = o._max ? o._max :3;
	this.min = o._min ? o._min :1;
	this.count = 0;
	this.w = o.width ? o.width : '142px';
	this.h = o.height ? o.height : '84px';
	this.uploadButton = o._addImage ? o._addImage : 'addImage';
	this.garbageImages = o.garbageImages ? o.garbageImages : 'garbageImages'; //要删除的图input name[]
}
ImageMultiUpload.prototype={};  
ImageMultiUpload.prototype.clearImageInput = function(_id)
{
	$('#url_'+ _id).val(''); 
}
ImageMultiUpload.prototype.setImgTag = function(_id, src)
{
	$('.img_div_' + _id).html("<img id='img_"+_id+"'  "+ ( src ? 'src="' + src + '"' : '')+" width='"+this.w+"' height='"+this.h+"'/>");
}
ImageMultiUpload.prototype.getAlt = function(obj)
{
	return $(obj).attr('alt');
}
ImageMultiUpload.prototype.change = function(obj)
{
	$('#file'+ this.getAlt(obj)).trigger('click');
}
ImageMultiUpload.prototype.remove = function(obj)
{
	var id = this.getAlt(obj);
	if(this.count > this.min)
	{
		this.count--;

		var img = $('#'+ id + ' input[name^='+ this.groupId +'_url]').val();
		if(img)
		{
			$('#' + this.groupId).append('<input type="hidden" name="'+ this.garbageImages +'[]" value="'+ img +'"/>');
		}

		$('#'+ id).remove();
	}/*else
	{
		//alert("至少上传 " +this.min+"个图");
		$('.img_div_' + id).html(''); //也可以放默认图
		this.clearImageInput(id);//每次选择图时 把之前 image url 清空
		this.setDisplay("del_" +id, 'none'); //没图时 隐藏 X
		return ;
	}*/
	this.createButton();
}
ImageMultiUpload.prototype.setDisplay = function(id, value)
{
	document.getElementById( id).style.display = value;
}
ImageMultiUpload.prototype.previews = function(obj)
{
	var id = this.getAlt(obj);
	var input = document.getElementById('file' + id);

	var fileList = input.files;
	for (var i = 0; i < fileList.length; i++) 
	{            
		this.setImgTag(id);

		var imgTag = document.getElementById("img_"+id); 
		if (input.files && input.files[i]) 
		{
			imgTag.style.display = 'block';
			imgTag.style.width = this.w;
			imgTag.style.height = this.h;
			imgTag.src = window.URL.createObjectURL(input.files[i]);
		}else 
		{
			input.select();
			var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById("img_"+id); 
			localImagId.style.width = this.w;
			localImagId.style.height = this.h;
			try 
			{
				localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			}catch (e) 
			{
				alert("您上传的图片格式不正确");
				return false;
			}
			imgTag.style.display = 'none';
			document.selection.empty();
		}

		//this.clearImageInput(id);//每次选择图时 把之前 image url 清空

		this.setDisplay("del_" +id, '');//选择好图时 显示 X
	}
	return true; 
}
ImageMultiUpload.prototype.createRandId = function()
{
	return 'r' + (new Date().getTime().toString()) + Math.ceil(Math.random() * 99999);
}
ImageMultiUpload.prototype.creates = function(imageUrl)
{
	if(imageUrl)
	{
		var imageUrls = imageUrl.split(',');
		this.count = imageUrls.length;
		for(var i = 0 ; i < this.count ;i++)
		{
			this.create(imageUrls[i]);
		}
	}
}
ImageMultiUpload.prototype.create = function(imageUrl)
{
	var rid = this.createRandId();

	var tmp = '<table id="'+rid+'" class="_'+this.groupId+'" style="float:left;width:150px;position:relative;cursor: pointer;overflow: hidden"><tr><td style="border:none;height:86px">';
	tmp += '<a id="del_'+rid+'" alt="'+rid+'" style="position:absolute;top:1px;right:8px;width:20px;height:20px;background: #333;overflow: hidden;"><span style="color:white;position:absolute;top:3px;right:7px">X</span></a><div class="img_div_'+rid+'" style="width:142px;height:84px;border:1px solid #ccc;" alt="'+rid+'"></div>';
	tmp += '</td><td style="position:absolute;border:none;">';
	tmp += '<div style="position:absolute;top:-18px"><input type="hidden" name="'+this.groupId+'Uploaded[]" value="'+imageUrl+'" id="url_'+rid+'">';
	tmp += '<input type="file" accept="image/*" style="display:none;" name="'+ this.groupId+'" id="file'+rid+'" alt="'+rid+'" />';
	tmp += '</div>';
	tmp += '</td></tr></table>';// multiple="multiple"

	$('#' + this.groupId).append(tmp);

	var _this = this;

	$(document).on('change' , "#file" + rid , function()
	{
		_this.previews(this);//显示选择的图
	});

	$(document).on('click' , ".img_div_" + rid , function()
	{
		_this.change(this);//添加
	});

	$(document).on('click' , "#del_" + rid, function()
	{
		_this.remove(this);//删除
	});

	if(imageUrl)
	{
		this.setImgTag(rid, imageUrl); //有图显示
	}else
	{
		this.count++;
	}
	this.createButton();
}
ImageMultiUpload.prototype.createButton = function()
{
	if(this.count < this.max)
	{
		$('.' +this.uploadButton).show();

	}else
	{
		$('.' +this.uploadButton).hide();
	}
}