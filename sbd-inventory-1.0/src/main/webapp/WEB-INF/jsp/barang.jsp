<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<c:if test="${em != null}">
		<div class="alert alert-danger alert-dismissable fade in">
	        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
	        <strong>Error!</strong> ${em}
	    </div>
	</c:if>
	<div class="row">
		<div class="col-md-6 col-md-offset-3 ">
			<div class="panel panel-primary">
				<div class="panel-heading">Tambah Barang</div>
				
				<div class="panel-body">
					<form:form method="post" modelAttribute="barang">
						<form:hidden path="idBarang"/>
						<fieldset class="form-group">
							<form:label path="namaBarang">Nama Barang</form:label>
							<form:input path="namaBarang" type="text" class="form-control" required="required"/>
							<form:errors path="namaBarang" cssClass="text-warning" />
						</fieldset>
						
						<fieldset class="form-group">
							<form:label path="merk">Merk</form:label>
							<form:input path="merk" type="text" class="form-control" required="required"/>
							<form:errors path="merk" cssClass="text-warning" />
						</fieldset>
						<c:if test="${idBarang > 0}">
					        <fieldset class="form-group">
								<form:label path="jumlahSisa">Jumlah yang dipindah</form:label>
								<form:input path="jumlahSisa" type="text" class="form-control" required="required"/>
								<form:errors path="jumlahSisa" cssClass="text-warning" />
							</fieldset>
						</c:if>
						<fieldset class="form-group">
							<form:label path="jumlahTotal">Jumlah Total</form:label>
							<form:input path="jumlahTotal" type="text" class="form-control" required="required"/>
							<form:errors path="jumlahTotal" cssClass="text-warning" />
						</fieldset>
						<fieldset class="form-group">
							<form:label path="kondisi">Kondisi</form:label>
							<form:select path="kondisi" type="text" class="form-control" items="${kondisi}"></form:select>
							<form:errors path="kondisi" cssClass="text-warning" />
						</fieldset>
						
						<fieldset class="form-group">
							<form:label path="idRuangan">Ruangan</form:label>
							<form:select path="idRuangan" type="text" class="form-control" items="${ruangan}" itemValue="idRuangan" itemLabel="namaRuangan"></form:select>
							<form:errors path="idRuangan" cssClass="text-warning" />
						</fieldset>
						
						<fieldset class="form-group">
							<form:label path="idPerolehan">Perolehan Barang</form:label>
							<form:select path="idPerolehan" type="text" class="form-control" items="${perolehanBarang}" itemValue="idPerolehan" itemLabel="sumberDana"></form:select>
							<form:errors path="idPerolehan" cssClass="text-warning" />
						</fieldset>

						<fieldset class="form-group">
							<form:label path="tanggalMaintain">Tanggal Maintain</form:label>
							<form:input path="tanggalMaintain" type="text" class="form-control" required="required"/>
							<form:errors path="tanggalMaintain" cssClass="text-warning" />
						</fieldset>
						
						<button type="submit" class="btn btn-success">Save</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
