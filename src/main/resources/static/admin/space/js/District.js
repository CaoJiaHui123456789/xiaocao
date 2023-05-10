$(document).ready(function () {
    const proid="#add_parkPro";
    const cityid="#add_parkCity";
    const disid="#add_parkDis";
    $(proid).combobox({
        onChange:function (record) {
            const responsedata=$(proid).combobox("getData");
            console.log(responsedata);
            let data=responsedata.filter(function(fp) {
                console.log($(proid).combobox("getValue"));
                return fp.code === $(proid).combobox("getValue");
            })[0].children;
            $(cityid).combobox("loadData",data);
            $(cityid).combobox({
                onChange:function (record) {
                    const responsedata=$(cityid).combobox("getData");
                    let data=responsedata.filter(function(fp) {
                        //console.log($("#sheng").combobox("getValue"));
                        return fp.code === $(cityid).combobox("getValue");
                    })[0].children;
                    $(disid).combobox("loadData",data);
                }

            });
        }
    });


});