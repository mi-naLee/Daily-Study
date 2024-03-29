<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">Board List Page
                        	<!-- 목록 페이지 상단에 register btn 정의 -->
                        	<button id='regBtn' type="button" class="btn btn-xs pull-right">
                        	Register New Board</button>
                        </div>
                        <div class="panel-body">
                        	<table class="table table-striped table-bordered table-hover">
                        		<thead>
                        			<tr>
                        				<th>#번호</th>
                        				<th>제목</th>
                        				<th>작성자</th>
                        				<th>작성일</th>
                        				<th>수정일</th>
                        			</tr>
                        		</thead>
                        		
                        		<c:forEach items="${list }" var="board">
                        			<tr>
                        				<td><c:out value="${board.bno }"/></td>
                        				<!-- 제목을 클릭했을 때 해당 게시물로 이동(새창 이동의 경우 target 속성 필요 -->
                        				<td>
                        					<a class='move' href='<c:out value="${board.bno }"/>'>
                        					<c:out value="${board.title }"/></a>
                        				</td>
                        				<td><c:out value="${board.writer }"/></td>
                        				<td><fmt:formatDate pattern="yyyy-MM-dd" 
                        				value="${board.regdate }"/></td>
                        				<td><fmt:formatDate pattern="yyyy-MM-dd" 
                        				value="${board.updateDate }"/></td>
									</tr>
                        		</c:forEach>
                        	</table>
                        	
                        	<!-- ===== Search 처리 ===== -->
                        	<div class='row'>
                        		<div class="col-lg-12">
                        			<form id='searchForm' action="/board/list" method='get'>
                        				<select name='type'>
                        					<option value=""
                        						<c:out value="${pageMaker.cri.type == null?'selected':''}"/>>
                        					--</option>
                        					<option value="T"
                        						<c:out value="${pageMaker.cri.type eq 'T'?'selected':''}"/>>
                        					제목</option>
                        					<option value="C"
                        						<c:out value="${pageMaker.cri.type eq 'C'?'selected':''}"/>>
                        					내용</option>
                        					<option value="W"
                        						<c:out value="${pageMaker.cri.type eq 'W'?'selected':''}"/>>
                        					작성자</option>
                        					<option value="TC"
                        						<c:out value="${pageMaker.cri.type eq 'TC'?'selected':''}"/>>
                        					제목 or 내용</option>
                        					<option value="TW"
                        						<c:out value="${pageMaker.cri.type eq 'TW'?'selected':''}"/>>
                        					제목 or 작성자</option>
                        					<option value="TWC"
                        						<c:out value="${pageMaker.cri.type eq 'TWC'?'selected':''}"/>>
                        					제목 or 내용 or 작성자</option>
                        				</select>
                        				<input type='text' name='keyword'
                        				value='<c:out value="${pageMaker.cri.keyword}"/>'/>
                        				<!-- (페이지 유지를 위해 pageNum, amount도 같이 이동) -->
		                        		<input type='hidden' name='pageNum' value="${pageMaker.cri.pageNum }">
                        				<input type='hidden' name='amount' value="${pageMaker.cri.amount }">
                        				<button class='btn btn-default'>Search</button>
                        			</form>
                        		</div>
                        	</div>
                        	
                        	<!-- ===== Paging 처리 ===== -->
                        	<div class='pull-right'>
                        		<ul class="pagination">
                        			<c:if test="${pageMaker.prev }">
                        				<li class="paginate_button previous">
                        					<a href="${pageMaker.startPage -1 }">Previous</a></li>
                        			</c:if>
                        			
                        			<c:forEach var="num" begin="${pageMaker.startPage }"
                        			end="${pageMaker.endPage}">
                        				<li class="paginate_button ${pageMaker.cri.pageNum == num ? "active":"" }">
                        					<a href="${num }">${num }</a></li>
                        			</c:forEach>
                        			
                        			<c:if test="${pageMaker.next }">
                        				<li class="paginate_button next">
                        					<a href="${pageMaker.endPage +1 }">Next</a></li>
                        			</c:if>
                        		</ul>
                        	</div>
                        	<form id='actionForm' action="/board/list" method='get'>
								<input type='hidden' name='pageNum' value="${pageMaker.cri.pageNum }">
                        		<input type='hidden' name='amount' value="${pageMaker.cri.amount }">   
                        		<input type='hidden' name='type' value='<c:out value="${pageMaker.cri.type}"/>'>   
                        		<input type='hidden' name='keyword' value='<c:out value="${pageMaker.cri.keyword}"/>'>   
                        	</form>
                        	
                        	<!-- ===== Modal 추가 ===== -->
                        	<!-- Modal -->
                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
                            aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" 
                                            aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                                        </div>
                                        <div class="modal-body">처리가 완료되었습니다.</div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" 
                                            data-dismiss="modal">Close</button>
                                            <button type="button" class="btn btn-primary">Save changes</button>
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                </div>
                                <!-- /.modal-dialog -->
                            </div>
                            <!-- /.modal -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
            </div>
            <!-- /.row -->
        
        <%@ include file="../includes/footer.jsp" %>
        
   <script type="text/javascript">
   		$(document).ready(
   			function(){
   			var result = '<c:out value="${result}"/>';/* register 후 result에 bno rtn */
   			
   			/* 모달창 띄우기 */
   			checkModal(result); // register 후 RedirectAttirbutes로 bno 전달받음
   			
   			// window.history 객체를 이용해 뒤로 가기를 눌렀을 때 모달창을 보이지 않기
   			history.replaceState({},null,null);
   			
   			function checkModal(result){
				if(result === ''|| history.state){
					return;
				}   		
				if(parseInt(result) > 0){
					$(".modal-body").html("게시글 "+parseInt(result)+" 번이 등록되었습니다.");
				}
				$("#myModal").modal("show");
   			}
   			
   			$("#regBtn").on("click",function(){
   				self.location = "/board/register"; // 목록 페이지 상단 register btn 클릭하면 등록 화면 이동
   			});
   			
   			var actionForm = $("#actionForm");
   			
   			// 이전/다음 버튼 클릭 event
   			$(".paginate_button a").on("click",function(e){
   				e.preventDefault(); // a 태그를 클릭했을 때 이동을 막음
   				
   				console.log('click');
   				
   				// pageNum 값을 href 속성값으로 변경
   				actionForm.find("input[name='pageNum']").val($(this).attr("href"));
   				actionForm.submit(); // 이동
   			});
   			
   			// 게시글의 제목을 클릭했을 때 pageNum과 amount 파라미터 추가 전달
   			$(".move").on("click",function(e){
   				e.preventDefault();
   				actionForm.append("<input type='hidden' name='bno' value='"+
   	   					$(this).attr("href")+"'>");
   				actionForm.attr("action","/board/get");
   				actionForm.submit();
   			});
   			
   			var searchForm = $("#searchForm");
   			
   			$("#searchForm button").on("click", function(e){
   				if(!searchForm.find("option:selected").val()){
   					alert("검색 종류를 선택하세요");
   					return false;
   				}
   				if(!searchForm.find("input[name='keyword']").val()){
   					alert("키워드를 입력하세요");
   					return false;
   				}
   				searchForm.find("input[name='pageNum']").val("1"); // 검색 후의 결과 페이지 1
   				e.preventDefault();
   				
   				searchForm.submit();
   			});
   		});
   </script>