$(document).ready(function () {
    getPosts();
})

function isValidContents(contents) {
    if (contents == '') {
        alert('내용을 입력해주세요');
        return false;
        }
    return true;
}

function editPost(id) {
    showEdits(id);

    let title = $(`#${id}-title`).text().trim();
    let contents = $(`#${id}-contents`).text().trim();

    $(`#${id}-titletextarea`).val(title);
    $(`#${id}-contentstextarea`).val(contents);
    }

function showEdits(id) {
    $(`#${id}-editarea`).show();
    $(`#${id}-submit`).show();
    $(`#${id}-delete`).show();

    $(`#${id}-username`).hide();
    $(`#${id}-title`).hide();
    $(`#${id}-contents`).hide();
    $(`#${id}-edit`).hide();
    }



function getPosts() {
    $.ajax({
    type: 'GET',
    url: '/api/posts',
    success: function (response) {
    for (let i = 0; i < response.length; i++) {
    let post = response[i];
    let id = post['id'];
    let title = post['title'];
    let author = post['author'];
    let contents = post['contents'];
    let modifiedAt = post['modifiedAt'];
    addHTML(id, title, author, contents, modifiedAt);
    }
    }
    })
    }

// 메모 하나를 HTML로 만들어서 body 태그 내 원하는 곳에 붙입니다.
function addHTML(id, title, author, contents, modifiedAt) {
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
                            <div id="${id}-title" class="text">
                                ${title}
                            </div>
                            <div id="${id}-contents" class="text">
                                ${contents}
                            </div>
                            <div id="${id}-editarea" class="edit">
                                <textarea id="${id}-authortextarea" class="te-edit" name="" id="" cols="30" rows="5"></textarea>
                                <textarea id="${id}-titletextarea" class="te-edit" name="" id="" cols="30" rows="5"></textarea>
                                <textarea id="${id}-contentstextarea" class="te-edit" name="" id="" cols="30" rows="5"></textarea>
                            </div>
                        </div>
                        <!-- 버튼 영역-->
                        <div class="footer">
                            <img id="${id}-edit" class="icon-start-edit" src="../images/edit.png" alt="" onclick="editPost(${id})">
                            <img id="${id}-delete" class="icon-delete" src="../images/delete.png" alt="" onclick="deleteOne(${id})">
                            <img id="${id}-submit" class="icon-end-edit" src="../images/done.png" alt="" onclick="submitEdit(${id})">
                            <button onclick="goToPage(${id})">댓글 보기</button>
                        </div>
                    </div>`;
    $('#cards-box').append(tempHtml);
}

// 메모를 생성합니다.
function writePost() {
    let title = $('#title').val();
    let author = $('#author').val();
    let contents = $('#contents').val();
    if (isValidContents(contents) == false || isValidContents(title) == false) {
    return;
}

    let data = {'title': title, 'author': author, 'contents': contents};

    $.ajax({
    type: "POST",
    url: "/api/posts",
    contentType: "application/json", // JSON 형식으로 전달함을 알리기
    data: JSON.stringify(data),
    success: function (response) {
    alert('메시지가 성공적으로 작성되었습니다.');
    window.location.reload();
    }
    });
    }


// 게시글 상세 페이지로 갑니다.
function goToPage(id) {
    window.location.href = "/forum/" + id;
}


// 메모를 수정합니다.
function submitEdit(id) {
    let author = $(`#${id}-authortextarea`).val().trim();
    let title = $(`#${id}-titletextarea`).val().trim();
    let contents = $(`#${id}-contentstextarea`).val().trim();

    if (isValidContents(contents) == false || isValidContents(title) == false || isValidContents(author) == false) {
    return;
    }

    let data = {'title': title, 'author': author, 'contents': contents};

    $.ajax({
    type: "PUT",
    url: `/api/posts/${id}`,
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
        url: `/api/posts/${id}`,
        success: function (response) {
            alert('메시지 삭제에 성공하였습니다.');
            window.location.reload();
        }
    })
    }