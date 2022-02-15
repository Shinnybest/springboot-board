$(document).ready(function () {
    // HTML 문서를 로드할 때마다 실행합니다.
    getForum();
    getComments();
})

function getForum() {
    $.ajax({
        type: 'GET',
        url: '/api/posts/{{id}}',
        success: function (response) {
                let post = response;
                let id = post['id'];
                let title = post['title'];
                let author = post['author'];
                let contents = post['contents'];
                let modifiedAt = post['modifiedAt'];
                addForum(id, title, author, contents, modifiedAt);
        }
    })
}

function addForum(id, title, author, contents, modifiedAt) {
    let temp_html = `<div class="card">
                        <!-- date/username 영역 -->
                        <div class="metadata">
                            <div class="date">
                                ${modifiedAt}
                            </div>
                            <div id="${id}-username" class="username">
                                ${author}
                            </div>
                        </div>
                        <!-- contents 조회/수정 영역-->
                        <div class="contents">
                            <div id="${id}-contents" class="text">
                                ${contents}
                            </div>
                            <div id="${id}-editarea" class="edit">
                                <textarea id="${id}-contentstextarea" class="te-edit" name="" id="" cols="30" rows="5"></textarea>
                            </div>
                        </div>
                        <!-- 버튼 영역-->
                        <div class="footer">
                            <img id="${id}-edit" class="icon-start-edit" src="../images/edit.png" alt="" onclick="editPost(${id})">
                            <img id="${id}-delete" class="icon-delete" src="../images/delete.png" alt="" onclick="deleteOne(${id})">
                            <img id="${id}-submit" class="icon-end-edit" src="../images/done.png" alt="" onclick="submitEdit(${id})">
                        </div>
                    </div>`
    $('#cards-box').append(temp_html);
}

function getComments(page_id) {
    $.ajax({
        type: 'GET',
        url: `/api/comments/{${page_id}}`,
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                let post = response[i];
                let id = post['id'];
                let author = post['author'];
                let contents = post['contents'];
                let modifiedAt = post['modifiedAt'];
                addHTML(id, author, contents, modifiedAt);
            }
        }
    })
}

function addHTML(id, author, contents, modifiedAt) {
    // 1. HTML 태그를 만듭니다.
    let tempHtml = `<div class="card">
                        <!-- date/username 영역 -->
                        <div class="metadata">
                            <div class="date">
                                ${modifiedAt}
                            </div>
                            <div id="${id}-username" class="username">
                                ${author}
                            </div>
                        </div>
                        <!-- contents 조회/수정 영역-->
                        <div class="contents">
                            <div id="${id}-contents" class="text">
                                ${contents}
                            </div>
                            <div id="${id}-editarea" class="edit">
                                <textarea id="${id}-contentstextarea" class="te-edit" name="" id="" cols="30" rows="5"></textarea>
                            </div>
                        </div>
                        <!-- 버튼 영역-->
                        <div class="footer">
                            <img id="${id}-edit" class="icon-start-edit" src="../images/edit.png" alt="" onclick="editPost(${id})">
                            <img id="${id}-delete" class="icon-delete" src="../images/delete.png" alt="" onclick="deleteOne(${id})">
                            <img id="${id}-submit" class="icon-end-edit" src="../images/done.png" alt="" onclick="submitEdit(${id})">
                        </div>
                    </div>`;
    // 2. #cards-box 에 HTML을 붙인다.
    $('#comments-box').append(tempHtml);
}

function writeComments() {
    let author = $('#author').val();
    let contents = $('#contents').val();
    // page_id 값을 어디서 받아올 수 있을까?
    // let page_id = $('#id').attr('id');

    let data = {'author': author, 'contents': contents, 'page_id': page_id}

    $.ajax({
        type: "POST",
        url: `/api/comments/${page_id}`,
        contentType: "application/json", // JSON 형식으로 전달함을 알리기
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지가 성공적으로 작성되었습니다.');
            window.location.reload();
        }
    });


}
