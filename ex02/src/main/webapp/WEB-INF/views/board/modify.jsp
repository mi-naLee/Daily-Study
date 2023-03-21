<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../includes/header.jsp" %>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Read</h1>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Read Page</div>
			<div class="panel-body">
				<form role="form" action="/board/modify" method="post">
				
					<!-- ===== 페이지 이동시 현재 페이지 + 현재 페이지의 게시글을 가지고 이동 ===== -->
					<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum }"/>'>
					<input type='hidden' name='amount' value='<c:out value="${cri.amount }"/>'>
					
					<!-- ===== 페이지 이동시 검색 조건 함께 이동 ===== -->
					<input type='hidden' name='type' value='<c:out value="${cri.type }"/>'>
					<input type='hidden' name='keyword' value='<c:out value="${cri.keyword }"/>'>
				
					<div class="form-group">
						<label>Bno</label><input class="form-control" name='bno'
						value='<c:out value="${board.bno }"/>' readonly="readonly">
					</div>
					<div class="form-group">
						<label>Title</label><input class="form-control" name='title'
						value='<c:out value="${board.title }"/>'>
					</div>
					<div class="form-group">
						<label>Text area</label><textarea class="form-control" rows="3" name='content'>
						<c:out value="${board.content }"/></textarea>
					</div>
					<div class="form-group">
						<label>Writer</label><input class="form-control" name='writer'
						value='<c:out value="${board.writer }"/>' readonly="readonly">
					</div>
					<!-- 등록일/수정일: BoardVO로 수집되어야 하므로 날짜 포맷을 맞춰준다. -->
					<div class="form-group">
						<label>RegDate</label><input class="form-control" name='regDate'
						value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.regdate}"/>' readonly="readonly">
					</div>
					<div class="form-group">
						<label>Update Date</label><input class="form-control" name='updateDate'
						value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.updateDate}"/>' readonly="readonly">
					</div>
					
					<button type="submit" data-oper='modify' class="btn btn-default">Modify</button>
					<button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>
					<button type="submit" data-oper='list' class="btn btn-info">List</button>
				</form>
			</div><!-- end panel-body -->
		</div><!-- end panel -->
	</div><!-- end col -->
</div><!-- end row -->
<%@ include file="../includes/footer.jsp" %>

<script type="text/javascript"> // 페이지 하단에 버튼 동작
	$(document).ready(function(){
		var formObj = $("form");
		
		$('button').on("click", function(e){
			e.preventDefault(); // form 태그의 submit 처리를 막고 하단에서 직접 submit 수행

			var operation = $(this).data("oper");
			
			console.log(operation);
			
			if(operation === 'remove'){
				formObj.attr("action", "/board/remove");
			}else if(operation === 'list'){ // 수정 페이지에서 리스트 버튼 클릭시 get method로 목록 페이지 이동
				formObj.attr("action", "/board/list").attr("method","get"); 
				// form 태그의 정보를 잠시 복사(clone)
				var pageNumTag = $("input[name='pageNum']").clone();
				var amountTag = $("input[name='amount']").clone();
				var keywordTag = $("input[name='keyword']").clone();
				var typeTag = $("input[name='type']").clone();
			
				formObj.empty(); // form 태그의 모든 내용 삭제
				// --> 모든 내용 삭제 후 pageNum과 amount를 다시 추가해 리스트 호출
				formObj.append(pageNumTag);
				formObj.append(amountTag);
				formObj.append(keywordTag);
				formObj.append(typeTag);
			}
			formObj.submit();
		});
	});
</script>