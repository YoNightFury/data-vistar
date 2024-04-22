/**
    * 
    * @param {*} param 
    * @param {string} name 
    */
export const validateInteger = function (param, name) {
    if (parseInt(param).toString()!==param+"") {
        throw new Error(`The ${name} should be a integer!`);
    }
}