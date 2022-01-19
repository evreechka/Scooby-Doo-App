<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <#if profile??>
        <form method="post" enctype="multipart/form-data" action="/profile/add">
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
                <#if photoError??>
                    <div class="alert alert-danger" role="alert">
                        ${photoError}
                    </div>
                </#if>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Character Id:</label>
                <div class="col-sm-6">
                    <input type="text" name="characterId"
                           class="form-control ${(idError??)?string('is-invalid', '')}"/>
                    <#if idError??>
                        <div class="invalid-feedback">
                            ${idError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Username:</label>
                <div class="col-sm-6">
                    <input type="text" name="username"
                           class="form-control ${(usernameError??)?string('is-invalid', '')}"/>
                    <#if usernameError??>
                        <div class="invalid-feedback">
                            ${usernameError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Password:</label>
                <div class="col-sm-6">
                    <input type="password" name="password"
                           class="form-control ${(passwordError??)?string('is-invalid', '')}"/>
                    <#if passwordError??>
                        <div class="invalid-feedback">
                            ${passwordError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Features</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="feature">
                    <option value="INTELLECT" selected>INTELLECT</option>
                    <option value="INTUITION">INTUITION</option>
                    <option value="SPEED">SPEED</option>
                    <option value="FEARLESS">FEARLESS</option>
                </select>
            </div>
            <button class="btn btn-dark" type="submit">
                Save
            </button>
        </form>
    </#if>
</@c.page>