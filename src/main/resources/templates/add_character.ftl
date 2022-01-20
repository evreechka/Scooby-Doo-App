<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <#if profile??>
        <form method="post" action="/character/add">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Name:</label>
                <div class="col-sm-6">
                    <input type="text" name="name"
                           class="form-control ${(nameError??)?string('is-invalid', '')}"/>
                    <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Surname:</label>
                <div class="col-sm-6">
                    <input type="text" name="surname"
                           class="form-control ${(surnameError??)?string('is-invalid', '')}"/>
                    <#if surnameError??>
                        <div class="invalid-feedback">
                            ${surnameError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Age:</label>
                <div class="col-sm-6">
                    <input type="text" name="age"
                           class="form-control ${(ageError??)?string('is-invalid', '')}"/>
                    <#if ageError??>
                        <div class="invalid-feedback">
                            ${ageError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Sex</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="sex">
                    <option value="FEMALE" selected>FEMALE</option>
                    <option value="MALE">MALE</option>
                    <option value="OTHER">OTHER</option>
                </select>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Role in System</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="role">
                    <option value="USER" selected>USER</option>
                    <option value="SHERIFF">SHERIFF</option>
                </select>
            </div>
            <button class="btn btn-dark" type="submit">
                Save
            </button>
        </form>
    </#if>
</@c.page>