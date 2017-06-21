<link href="https://cdn.bootcss.com/bootstrap-fileinput/4.4.1/css/fileinput.min.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.1/js/fileinput.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.1/js/locales/zh.min.js"></script>

<script src="/javascript/file_upload.js"></script>

<div id="fileUploadModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span></button>
                <h4 class="modal-title">
                    <span class="fa fa-cloud-upload"></span>
                    <span>图片上传</span>
                </h4>
            </div>
            <div class="modal-body">
                <p>
                    <input id="upload" name="data" type="file" class="file-loading">
                </p>
            </div>
        </div>
    </div>
</div>