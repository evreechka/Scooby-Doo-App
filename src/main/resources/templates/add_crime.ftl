<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <#if profile??>
        <h3>Create Crime</h3>
        <form method="post" action="/crime/add">
            <div><b>Crime data</b></div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Crime description:</label>
                <input type="text" name="crimeDescription"
                          class="form-control ${(crimeDescriptionError??)?string('is-invalid', '')}">
                </input>
                <#if crimeDescriptionError??>
                    <div class="invalid-feedback">
                        ${crimeDescriptionError}
                    </div>
                </#if>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">fee:</label>
                <div class="col-sm-6">
                    <input type="text" name="fee"
                           class="form-control ${(feeError??)?string('is-invalid', '')}"/>
                    <#if feeError??>
                        <div class="invalid-feedback">
                            ${feeError}
                        </div>
                    </#if>
                </div>
            </div>
            <div><b>Choose Sheriff</b></div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Sheriffs</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="sheriffId">
                    <#list sheriffs as sheriff>
                        <option value="${sheriff.getId()?c}" selected>${sheriff.getName()} ${sheriff.getSurname()}</option>
                    </#list>
                </select>
            </div>
            <div><b>Choose Investigators</b></div>
            <div class="form-check">
                <#list investigators as inv>
                    <div>
                        <input class="form-check-input" type="checkbox" value="${inv.getInvestigatorId()?c}"
                               id="${inv.getInvestigatorId()}" name="invIds">
                        <label class="form-check-label" for="${inv.getInvestigatorId()}" style="word-break: break-all">
                            ${inv.getCharacter().getName()} ${inv.getCharacter().getSurname()}
                        </label>
                    </div>
                </#list>
            </div>
            <div><b>Choose Crime Scene</b></div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Crime Scenes</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="crimeSceneId">
                    <#list crime_scenes as scene>
                    <option value="${scene.getId()?c}" selected>${scene.getName()} ${scene.getPlace()}</option>
                    </#list>
                </select>
            </div>
            <div><b>Victim data</b></div>
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
            <div><b>Contention data</b></div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Contention description:</label>
                <input type="text" name="contentionDescription"
                          class="form-control ${(contentionDescriptionError??)?string('is-invalid', '')}"></input>
                <#if contentionDescriptionError??>
                    <div class="invalid-feedback">
                        ${contentionDescriptionError}
                    </div>
                </#if>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Damage Critically:</label>
                <div class="col-sm-6">
                    <input type="text" name="damageCritically"
                           class="form-control ${(damageCriticallyError??)?string('is-invalid', '')}"/>
                    <#if damageCriticallyError??>
                        <div class="invalid-feedback">
                            ${damageCriticallyError}
                        </div>
                    </#if>
                </div>
            </div>
            <div><b>Monster data</b></div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Name:</label>
                <div class="col-sm-6">
                    <input type="text" name="monsterName"
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
            <div><b>Criminal Case data</b></div>
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
            <div><b>Crime Visit data</b></div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Visit Destruction:</label>
                <div class="col-sm-6">
                    <input type="text" name="severityDestruction"
                           class="form-control ${(severityDestructionError??)?string('is-invalid', '')}"/>
                    <#if severityDestructionError??>
                        <div class="invalid-feedback">
                            ${severityDestructionError}
                        </div>
                    </#if>
                </div>
            </div>
            <button class="btn btn-dark" type="submit">
                Create
            </button>
        </form>
    </#if>
</@c.page>