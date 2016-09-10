/**
 * Created by songzhimao on 16-4-18.
 */
console.log("szm is here");
requirejs(["module1"], function(module1) {
    //This function is called when scripts/helper/util.js is loaded.
    //If util.js calls define(), then this function is not fired until
    //util's dependencies have loaded, and the util argument will hold
    //the module value for "helper/util".
    console.log("load end");
    console.log(module1);
});