$(document).ready(function () {
    let id = $('.getidxnum').attr('id');
    getPostandComments(id);
})

function isValidContents(contents) {
    if (contents == '') {
        alert('댓글 내용을 입력해주세요');
        return false;
    }
    return true;
}

function editPost(id) {
    showEdits(id);

    let author = $(`#${id}-username`).text().trim();
    let contents = $(`#${id}-comment-contents`).text().trim();
    $(`#${id}-authortextarea`).val(author);
    $(`#${id}-contentstextarea`).val(contents);
}

function showEdits(id) {
    $(`#${id}-editarea`).show();
    $(`#${id}-submit`).show();
    $(`#${id}-delete`).show();

    $(`#${id}-username`).hide();
    $(`#${id}-comment-contents`).hide();
    $(`#${id}-edit`).hide();
}



function getPostandComments(id) {
    $.ajax({
        type: 'GET',
        url: `/api/posts/${id}`,
        success: function (response) {
                let post = response;
                let postId = response['id'];
                let modifiedAt = post['modifiedAt'];
                let title = post['title'];
                let author = post['author'];
                let contents = post['contents'];
                let comments = post['comments'];
                for (let i=0; i<comments.length; i++) {
                    let modifiedAt = post['modifiedAt'];
                    let commentsId = post['id'];
                    let author = post['author'];
                    let comment = post['comment'];
                    addCommentsHTML(modifiedAt, commentsId, author, comment);
                }
                addPostHTML(modifiedAt, title, author, contents);
                addWriteHTML(postId);
        }
    })
}

function addPostHTML(modifiedAt, title, author, contents) {
    let temp_html = `<div class="card">
                        <!-- date/username 영역 -->
                        <div class="metadata">
                            <div class="date">
                                ${modifiedAt}
                            </div>
                            <div id="username" class="username">
                                ${author}
                            </div>
                        </div>
                        <!-- contents 조회/수정 영역-->
                        <div class="contents">
                            <div id="title" class="text">
                                ${title}
                            </div>
                            <div id="contents" class="text">
                                ${contents}
                            </div>
                        </div>
                    </div>`;
    $('#cards-box').append(temp_html);
}

function addCommentsHTML(modifiedAt, commentsId, author, comment) {
    let temp_html = `<div class="comment">
                        <!-- date/username 영역 -->
                        <div class="metadata">
                            <div class="date">
                                ${modifiedAt}
                            </div>
                            <div id="${commentsId}-username" class="username">
                                ${author}
                                
                            </div>
                        </div>
                        <!-- contents 조회/수정 영역-->
                        <div class="comment-contents">
                            <div id="${commentsId}-comment-contents" class="text">
                                ${comment}
                            </div>
                            <div id="${commentsId}-editarea" class="edit">
                                <textarea id="${commentsId}-authortextarea" class="te-edit" name="" id="" cols="30" rows="5"></textarea>
                                <textarea id="${commentsId}-contentstextarea" class="te-edit" name="" id="" cols="30" rows="5"></textarea>
                            </div>
                        </div>
                        <!-- 버튼 영역-->
                        <div class="footer">
                            <img id="${commentsId}-edit" class="icon-start-edit" src="../images/edit.png" alt="" onclick="editPost(${commentsId})">
                            <img id="${commentsId}-delete" class="icon-delete" src="../images/delete.png" alt="" onclick="deleteOne(${commentsId})">
                            <img id="${commentsId}-submit" class="icon-end-edit" src="../images/done.png" alt="" onclick="submitEdit(${commentsId})">
                        </div>
                    </div>`;
    $('#comments-box').append(temp_html);
}

function addWriteHTML(postId) {
    let temp_html = `<div class="area-write">
                        <textarea class="field" placeholder="댓글 작성자(유저명)" name="author" id="author" cols="10"
                                  rows="1"></textarea>
                        <textarea class="field" placeholder="동료에게 응원 한마디!" name="contents" id="write-comment-contents" cols="20"
                                  rows="1"></textarea>
                        <img src="../images/send.png" alt="" onclick="writeComments(${postId})">
                    </div>`
    $('#write-box').append(temp_html);
}

function writeComments(postsId) {
    let author = $('#author').val();
    let comment = $('#write-comment-contents').val();
    console.log(author, comment);

    // return 의미 이해하기
    if (isValidContents(author) == false || isValidContents(comment) == false) {
        return;
    }

    let data = {'author': author, 'comment': comment}

    $.ajax({
        type: "POST",
        url: `/api/comments/${postsId}`,
        contentType: "application/json", // JSON 형식으로 전달함을 알리기
        data: JSON.stringify(data),
        success: function (response) {
            alert('댓글이 성공적으로 작성되었습니다.');
            window.location.reload();
        }
    });
}

// 메모를 수정합니다.
function submitEdit(id) {
    let author = $(`#${id}-authortextarea`).val().trim();
    let comment = $(`#${id}-contentstextarea`).val().trim();

    if (isValidContents(comment) == false || isValidContents(author) == false) {
        return;
    }

    let data = {'author': author, 'comment': comment};

    $.ajax({
        type: "PUT",
        url: `/api/comments/${id}`,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지 변경에 성공하였습니다.');
            window.location.reload();
        }
    });
}

// 메모를 삭제합니다.
function deleteOne(id) {
    $.ajax({
        type: "DELETE",
        url: `/api/comments/${id}`,
        success: function (response) {
            alert('메시지 삭제에 성공하였습니다.');
            window.location.reload();
        }
    })
}