<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
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
						
						<fieldset class="form-group">
							<form:label path="jumlah">Jumlah</form:label>
							<form:input path="jumlah" type="text" class="form-control" required="required"/>
							<form:errors path="jumlah" cssClass="text-warning" />
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
<%@ include file="common/footer.jspf"%>