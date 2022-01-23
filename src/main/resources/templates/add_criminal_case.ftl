<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <#if profile??>
        <h3>Create Criminal case for Crime ${crimeId}</h3>
        <form method="post" action="/criminal_case/${crimeId?c}/add">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Severity:</label>
                <div class="col-sm-6">
                    <input type="text" name="severity"
                           class="form-control ${(severityError??)?string('is-invalid', '')}"/>
                    <#if severityError??>
                        <div class="invalid-feedback">
                            ${severityError}
                        </div>
                    </#if>
                </div>
            </div>
            <div><b>Monster data</b></div>
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
                <label class="col-sm-2 col-form-label">Monster description:</label>
                <input type="text" name="monsterDescription"
                          class="form-control ${(descriptionError??)?string('is-invalid', '')}"></input>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Weight:</label>
                <div class="col-sm-6">
                    <input type="text" name="weight"
                           class="form-control ${(weightError??)?string('is-invalid', '')}"/>
                    <#if weightError??>
                        <div class="invalid-feedback">
                            ${weightError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Height:</label>
                <div class="col-sm-6">
                    <input type="text" name="height"
                           class="form-control ${(heightError??)?string('is-invalid', '')}"/>
                    <#if heightError??>
                        <div class="invalid-feedback">
                            ${heightError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Monster type</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="type">
                    <option value="Witch" selected>Witch</option>
                    <option value="Zombie">Zombie</option>
                    <option value="Fantom">Fantom</option>
                    <option value="Vampire">Vampire</option>
                    <option value="Ghost">Ghost</option>
                    <option value="Goblin">Goblin</option>
                    <option value="Aliens">Aliens</option>
                    <option value="Fish">Fish</option>
                    <option value="Fairy">Fairy</option>
                    <option value="Pirate">Pirate</option>
                    <option value="Toxic">Toxic</option>
                    <option value="Dinosaur">Dinosaur</option>
                    <option value="Alligator">Alligator</option>
                    <option value="FireBender">FireBender</option>
                    <option value="Werewolf">Werewolf</option>
                    <option value="Beast">Beast</option>
                    <option value="Bird">Bird</option>
                    <option value="Skeleton">Skeleton</option>
                    <option value="Bully">Bully</option>
                </select>
            </div>
            <button class="btn btn-dark" type="submit">
                Save
            </button>
        </form>
    </#if>
</@c.page>