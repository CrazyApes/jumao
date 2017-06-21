var upload = {
    show: function() {
        $('#upload').fileinput('refresh');
        $('#fileUploadModal').modal('show');
    },
    hide: function () {
        $('#fileUploadModal').modal('hide');
    }
};

$(function () {
    $('#upload').fileinput({
        language: 'zh',
        showUpload: true,
        dropZoneEnabled: false,
        allowedFileExtensions: ['jpg', 'jpeg', 'png'],
        enctype: 'multipart/form-data',
        uploadUrl: '/rest/images',
        maxFileSize: 2048,
        uploadExtraData: {
            type: 'EMPLOYEE_HEADER_IMAGE'
        }
    });

    $('#upload').on('fileuploaded', function(event, data, previewId, index) {
        var result = data.response;
        console.log(result);
        $('#upload').fileinput('refresh');
        upload.hide();
    });
});