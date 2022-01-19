<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
    <div>
        <svg width="50" height="50" viewBox="0 0 16 14" class="bi bi-file-person" fill="currentColor"
             xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd"
                  d="M4 1h8a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2zm0 1a1 1 0 0 0-1 1v10a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V3a1 1 0 0 0-1-1H4z"/>
            <path d="M13.784 14c-.497-1.27-1.988-3-5.784-3s-5.287 1.73-5.784 3h11.568z"/>
            <path fill-rule="evenodd" d="M8 10a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
        </svg>
        <b style="font-size: 30px">${profile.getUsername()}</b>
    </div>

    <#if success??>
        <div class="alert alert-success" role="alert">
            ${success}
        </div>
    </#if>
    <#if error??>
        <div class="alert alert-danger" role="alert">
            ${error}
        </div>
    </#if>
    <div id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data" action="/profile/${activeId}/edit">
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
                    <label class="col-sm-2 col-form-label">Username:</label>
                    <div class="col-sm-6">
                        <input type="text" name="username" class="form-control ${(usernameError??)?string('is-invalid', '')}"
                              value="${profile.getUsername()}"/>
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
                               class="form-control ${(passwordError??)?string('is-invalid', '')}" placeholder="New password"/>
                        <#if passwordError??>
                            <div class="invalid-feedback">
                                ${passwordError}
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
                <div class="form-group">
                    <button type="submit" class="btn btn-dark">Save</button>
                </div>
            </form>
        </div>
    </div>
</@c.page>