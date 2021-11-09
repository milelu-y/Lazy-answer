package com.lazy.web.controller.vm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lazy.common.vm.Ellipsoid;
import com.lazy.vm.domain.vo.ExperimentResult;
import com.lazy.vm.domain.vo.PointBLH;
import com.lazy.vm.domain.vo.PointXYZ;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lazy.common.annotation.Log;
import com.lazy.common.core.controller.BaseController;
import com.lazy.common.core.domain.AjaxResult;
import com.lazy.common.enums.BusinessType;
import com.lazy.vm.domain.Experiment;
import com.lazy.vm.service.IExperimentService;
import com.lazy.common.utils.poi.ExcelUtil;
import com.lazy.common.core.page.TableDataInfo;

/**
 * 实验模块Controller
 *
 * @author fgq
 * @date 2021-10-27
 */
@RestController
@RequestMapping("/vm/Experiment")
public class ExperimentController extends BaseController
{
    @Autowired
    private IExperimentService experimentService;

    /**
     * 查询实验模块列表
     */
    @PreAuthorize("@ss.hasPermi('vm:Experiment:list')")
    @GetMapping("/list")
    public TableDataInfo list(Experiment experiment)
    {
        startPage();
        List<Experiment> list = experimentService.selectExperimentList(experiment);
        return getDataTable(list);
    }

    /**
     * 导出实验模块列表
     */
    @PreAuthorize("@ss.hasPermi('vm:Experiment:export')")
    @Log(title = "实验模块", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Experiment experiment)
    {
        List<Experiment> list = experimentService.selectExperimentList(experiment);
        ExcelUtil<Experiment> util = new ExcelUtil<Experiment>(Experiment.class);
        return util.exportExcel(list, "实验模块数据");
    }

    /**
     * 获取实验模块详细信息
     */
    @PreAuthorize("@ss.hasPermi('vm:Experiment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(experimentService.selectExperimentById(id));
    }

    /**
     * 新增实验模块
     */
    @PreAuthorize("@ss.hasPermi('vm:Experiment:add')")
    @Log(title = "实验模块", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Experiment experiment)
    {
        return toAjax(experimentService.insertExperiment(experiment));
    }

    /**
     * 修改实验模块
     */
    @PreAuthorize("@ss.hasPermi('vm:Experiment:edit')")
    @Log(title = "实验模块", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Experiment experiment)
    {
        return toAjax(experimentService.updateExperiment(experiment));
    }

    /**
     * 删除实验模块
     */
    @PreAuthorize("@ss.hasPermi('vm:Experiment:remove')")
    @Log(title = "实验模块", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(experimentService.deleteExperimentByIds(ids));
    }






    /**
     * 大地坐标转地心地固
     * @param //bLH 大地坐标
     * @return
     */
    @PostMapping("/BLHToXYZ")
    //public AjaxResult BLHToXYZ(@RequestBody PointBLH bLH)
    public AjaxResult BLHToXYZ()
    {
        PointBLH bLH = new PointBLH();
        bLH.setB(1223.1);
        bLH.setB(132423);
        bLH.setL(142434);
        Ellipsoid ellipsoid = new Ellipsoid();

        double B = bLH.B;
        double L = bLH.L;
        double H = bLH.H;
        //辅助计算公式
        double W = Math.sqrt(1 - ellipsoid.ec * Math.pow(Math.sin(B), 2));
        double N = ellipsoid.a / W;
        PointXYZ xYZ = new PointXYZ();
        xYZ.X = (N + H) * Math.cos(B) * Math.cos(L);
        xYZ.Y = (N + H) * Math.cos(B) * Math.sin(L);
        xYZ.Z = (N * (1 - ellipsoid.ec) + H) * Math.sin(B);
        Map<String, Object> data = new HashMap<>();
        data.put("input", bLH);
        data.put("output", xYZ);
        return AjaxResult.success(data);
    }



    /**
     * 地心地固转大地坐标
     * @param  //xYZ  地心地固坐标
     * @return
     */
    @PostMapping("/XYZToBLH")
    public AjaxResult XYZToBLH()
    {
        PointXYZ xYZl = new PointXYZ();
        xYZl.setX(-2195922.235);
        xYZl.setY(-5177499.073);
        xYZl.setZ(-299883.118);
        Ellipsoid ellipsoid = new Ellipsoid();
        double X = xYZl.X;
        double Y = xYZl.Y;
        double Z = xYZl.Z;
        PointBLH bLH = new PointBLH();
        bLH.L = Math.atan(Y / X);
        if (X < 0)
            bLH.L += Math.PI;
        double r = Math.sqrt(X * X + Y * Y);
        double B1 = Math.atan(Z / r);
        double B2;
        while (true)
        {
            double W1 =Math.sqrt(1 - ellipsoid.ec * (Math.sin(B1) * Math.sin(B1)));
            double N1 = ellipsoid.a / W1;
            B2 = Math.atan((Z + N1 * ellipsoid.ec * Math.sin(B1)) / r);
            if (Math.abs(B2 - B1) <= 0.0000000001)
                break;
            B1 = B2;
        }
        bLH.B = B2;
        double W = Math.sqrt(1 - ellipsoid.ec * (Math.sin(B2) * Math.sin(B2)));
        double N = ellipsoid.a / W;
        bLH.H = r / Math.cos(B2) - N;
        Map<String, Object> data = new HashMap<>();
        data.put("input", xYZl);
        data.put("output", bLH);
        return AjaxResult.success(data);
    }




    public PointBLH XYZToBLHClass(PointXYZ xYZ)
    {
        Ellipsoid ellipsoid = new Ellipsoid();
        double X = xYZ.X;
        double Y = xYZ.Y;
        double Z = xYZ.Z;
        PointBLH bLH = new PointBLH();
        bLH.L = Math.atan(Y / X);
        if (X < 0)
            bLH.L += Math.PI;
        double r = Math.sqrt(X * X + Y * Y);
        double B1 = Math.atan(Z / r);
        double B2;
        while (true)
        {
            double W1 =Math.sqrt(1 - ellipsoid.ec * (Math.sin(B1) * Math.sin(B1)));
            double N1 = ellipsoid.a / W1;
            B2 = Math.atan((Z + N1 * ellipsoid.ec * Math.sin(B1)) / r);
            if (Math.abs(B2 - B1) <= 0.0000000001)
                break;
            B1 = B2;
        }
        bLH.B = B2;
        double W = Math.sqrt(1 - ellipsoid.ec * (Math.sin(B2) * Math.sin(B2)));
        double N = ellipsoid.a / W;
        bLH.H = r / Math.cos(B2) - N;
        return bLH;
    }


    /**
     * 地心地固直角坐标系与东北天坐标系的转换实验
     * ref_xyz: 参考站的位置：也是地心地固直角坐标系
     *  xyz ： 随机输入的地心地固直角坐标系
     * @param
     */
    @PostMapping("/XYZ2ENU")
    public AjaxResult XYZ2ENU(){

        PointXYZ ref_xyz = new PointXYZ();
        ref_xyz.setX(-2195922.235);
        ref_xyz.setY(5177499.073);
        ref_xyz.setZ(299883.118);

        PointXYZ xyz = new PointXYZ();
        xyz.setX(-225922.235);
        xyz.setY(5773299.073);
        xyz.setZ(293483.118);

        PointBLH pointBLH = XYZToBLHClass(ref_xyz);
        double b =    pointBLH.getB();
        double l =    pointBLH.getL();
       // double h =    pointBLH.getH();
        double diff_x = ref_xyz.getX() - xyz.getX();
        double diff_y = ref_xyz.getY() - xyz.getY();
        double diff_z = ref_xyz.getZ() - xyz.getZ();

        double e = -Math.sin(l)*diff_x + Math.cos(l)*diff_y;
        double n = -Math.sin(b)*Math.cos(l)*diff_x + (-Math.sin(b)*Math.sin(l))*diff_y + Math.cos(b)*diff_z;
        double u = Math.cos(b)*Math.cos(l)*diff_x + Math.cos(b)*Math.sin(l)*diff_y + Math.sin(b)*diff_z;
        PointXYZ eun = new PointXYZ();
        eun.setE(e);
        eun.setE(n);
        eun.setE(u);
        /*System.out.println("e "+ e);
        System.out.println("n "+ n);
        System.out.println("u "+ u);*/

        Map<String, Object> data = new HashMap<>();
        data.put("input", ref_xyz);
        data.put("input2", pointBLH);
        data.put("output", eun);
        return  AjaxResult.success(data);
    }


    public PointXYZ XYZ2ENUClass(PointXYZ ref_xyz, PointXYZ xyz){
        PointBLH pointBLH = XYZToBLHClass(ref_xyz);
        double b =    pointBLH.getB();
        double l =    pointBLH.getL();
        // double h =    pointBLH.getH();s
        double diff_x = ref_xyz.getX() - xyz.getX();
        double diff_y = ref_xyz.getY() - xyz.getY();
        double diff_z = ref_xyz.getZ() - xyz.getZ();

        double e = -Math.sin(l)*diff_x + Math.cos(l)*diff_y;
        double n = -Math.sin(b)*Math.cos(l)*diff_x + (-Math.sin(b)*Math.sin(l))*diff_y + Math.cos(b)*diff_z;
        double u = Math.cos(b)*Math.cos(l)*diff_x + Math.cos(b)*Math.sin(l)*diff_y + Math.sin(b)*diff_z;
        PointXYZ eun = new PointXYZ();
        eun.setE(e);
        eun.setE(n);
        eun.setE(u);
        /*System.out.println("e "+ e);
        System.out.println("n "+ n);
        System.out.println("u "+ u);*/
        return  eun;
    }



    public Double dateToM(String dates){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(dates );
        } catch (Exception e) {
            e.printStackTrace();
        }

        double t = date.getTime();
        return t;
    }



    //根据历书计算卫星位置实验
    /**
     * 1
     * 取自中轨MEO值，72页，表4.4
     * @param    //toa 97200
     * @param // t  单位 秒
     */
    @PostMapping("/exp5_BDCS_LS")
    public AjaxResult exp5_BDCS_LS(){


       String date = "2020-12-31 22:0:0";

        double t = dateToM(date);
        ExperimentResult  experimentResult = new ExperimentResult();

        double toa = 97200;
        double tk = t-toa;
        if (tk>302400){
            tk= tk-604800;
        }else if(tk<-302400){
            tk=tk+604800;
        }
        experimentResult.setResult1(tk);
        //System.out.println(tk);

        double root_A = 5.282622985840*1000;
        // 长半轴
        double A = root_A*root_A;
        experimentResult.setResult2(A);

        // 平均运动角速度
        double u = 3.986004418*Math.pow(10,14);
        double n0 = Math.sqrt(u/Math.pow(A,3));
        experimentResult.setResult3(n0);

        // 平近角点
        double M0 = 6.138553273441*0.1;
        double Mk = M0 + n0*tk;
        experimentResult.setResult4(Mk);

        // 偏近点角
        double e = 2.297864761204*Math.pow(10,-11);

        double B1 = Mk;
        double B2;
        while (true)
        {
            B2 = Mk - e*Math.sin(B1);
            if (Math.abs(B2 - B1) <= 0.0000000001)
                break;
            B1 = B2;
        }

        double Ek = B2;
        experimentResult.setResult5(Ek);
       /* System.out.println("Ek"  +Ek);
        System.out.println("Mk"  +Mk);
        System.out.println("other"  +e*Math.sin(Ek));*/

//        真近点角
        double vk_tmp1 = 1-e*Math.cos(Ek);
        double sinvk = (Math.sin(Ek)*Math.sqrt(1-e*e))/vk_tmp1;
        double cosvk = (Math.cos(Ek)-e)/vk_tmp1;
        double vk = Math.acos(cosvk);
        experimentResult.setResult6(vk);

//        纬度副角
        double oniga = -2.118684675401;
        double sita0 = vk + oniga;
        experimentResult.setResult7(sita0);

//        径向距离
        double rk = A*(1-e*Math.cos(Ek));
        experimentResult.setResult8(rk);

//        轨道平面内的坐标
        double xk = rk*Math.cos(sita0);
        double yk = rk*Math.sin(sita0);
        experimentResult.setResult9(Double.valueOf(xk+","+yk));

//        升交点经度
        double Big_oniga = -1.039539674394;
        double Big_onigaP = -7.055651039044*Math.pow(10,-9);
        double Big_onigaPE = 7.2921150*Math.pow(10,-5);
        double Big_onigak = Big_oniga + (Big_onigaP-Big_onigaPE)*tk - Big_onigaPE*toa;
        experimentResult.setResult10(Big_onigak);

//        轨道倾斜角
        double i0 = 0.3*Math.PI;
        double sigta = 4.178745490094*Math.pow(10,-11);
        double ii = i0 + sigta;
        experimentResult.setResult11(ii);

//        CGS2000坐标
        double Xk = xk*Math.cos(Big_onigak)-yk*Math.cos(ii)*Math.sin(Big_onigak);
        double Yk = xk*Math.sin(Big_onigak)+yk*Math.cos(ii)*Math.cos(Big_onigak);
        double Zk = yk*Math.sin(ii);
        experimentResult.setResult12(Double.valueOf(Xk+","+Yk+","+Zk));

        Map<String, Object> data = new HashMap<>();
        data.put("input", t);
        data.put("output", experimentResult);

        return  AjaxResult.success(data);
    }


    /** 根据北斗三号,MEO
     * t 单位 秒
     * @param //t
     */
    @PostMapping("/exp6_BDCS_XL")
    public AjaxResult exp6_BDCS_XL(){


        String date = "2020-12-31 22:0:0";

        double t = dateToM(date);

        ExperimentResult  experimentResult = new ExperimentResult();

        double toe = 1.728*Math.pow(10,5);
        double tk = t-toe;
        if (tk>302400){
            tk= tk-604800;
        }else if(tk<-302400){
            tk=tk+604800;
        }
        //System.out.println(tk);
        experimentResult.setResult1(tk);

        // 长半轴
        double Aref = 27906100;
        double A = Aref + (-29.9921875);
        experimentResult.setResult2(A);

        double ai = 3.354261313149*Math.pow(10,-14);
        double T = A - ai*tk;
        experimentResult.setResult3(T);



        // 平均运动角速度
        double u = 3.986004418*Math.pow(10,14);
        double n0 = Math.sqrt(u/Math.pow(A,3));

        experimentResult.setResult4(n0);

//        平均运动角速率的偏差
        double delta_n0 = 4.160173287916*Math.pow(10,-9);
        double delta_n0_pei = -1.053772269961*Math.pow(10,-13);
        double delta_na = delta_n0 + 0.5*delta_n0_pei * tk;
        experimentResult.setResult5(delta_na);

//       改正后的平均运动角速率
        double nA = n0 + delta_na;
        experimentResult.setResult6(nA);

        // 平近角点
        double M0 = -1.278362820234;
        double Mk = M0 + nA*tk;
        experimentResult.setResult7(Mk);

        // 偏近点角
        double e = 1.014769659378*Math.pow(10,-4);
        experimentResult.setResult8(e);

        double B1 = Mk;
        double B2;
        while (true)
        {
            B2 = Mk - e*Math.sin(B1);
            if (Math.abs(B2 - B1) <= 0.0000000001)
                break;
            B1 = B2;
        }

        double Ek = B2;
        /*System.out.println("Ek"  +Ek);
        System.out.println("Mk"  +Mk);
        System.out.println("other"  +e*Math.sin(Ek));*/

//        真近点角
        double vk_tmp1 = 1-e*Math.cos(Ek);
        double sinvk = (Math.sin(Ek)*Math.sqrt(1-e*e))/vk_tmp1;
        double cosvk = (Math.cos(Ek)-e)/vk_tmp1;

        double vk = Math.acos(cosvk);
        experimentResult.setResult9(vk);

//        纬度副角
        double oniga = -2.118684675401;
        double sita0 = vk + oniga;

        experimentResult.setResult10(sita0);

        //        改正项，纬度幅度、地心、轨道倾角
        double O_k = vk + 1.742683660307;
        double C_us = 6.177462637424*Math.pow(10,-6);
        double C_uc = 4.138797521591*Math.pow(10,-6);
        double C_rs = 83.27343750000;
        double C_rc = 232.1757812500;
        double C_is = -4.842877388000*Math.pow(10,-8);
        double C_ic = 1.862645149231*Math.pow(10,-9);
        double sige_uk = C_us*Math.sin(2*O_k)+C_uc*Math.cos(2*O_k);
        double sige_rk = C_rs*Math.sin(2*O_k)+C_rc*Math.cos(2*O_k);
        double sige_ik = C_is*Math.sin(2*O_k)+C_ic*Math.cos(2*O_k);
        experimentResult.setResult11(Double.valueOf(sige_uk+","+sige_rk+","+sige_ik));

//
//        改正后参数
        double uk = O_k + sige_uk;
        experimentResult.setResult13(uk);
//        径向距离
        double rk = A*(1-e*Math.cos(Ek)) + sige_rk;
        experimentResult.setResult16(rk);

//        轨道倾斜角,北斗二号
        double i0 = 0.9567195685131;
        double i0_pei = -7.1324399519*Math.pow(10,-10);
        double ik = i0 + i0_pei*tk + sige_ik;
//        轨道平面内的坐标
        double xk = rk*Math.cos(uk);
        double yk = rk*Math.sin(uk);
        experimentResult.setResult17(Double.valueOf(xk+","+yk));


//        升交点经度
        double Big_oniga = 1.844942331859;
        double Big_oniga_pei = -7.095652705274*Math.pow(10,-9);
        double Big_onigaE_pei = 7.2921150*Math.pow(10,-5);
        double Big_onigak = Big_oniga + (Big_oniga_pei-Big_onigaE_pei)*tk - Big_onigaE_pei*toe;
        experimentResult.setResult18(Big_onigak);

//        CGS2000坐标
        double Xk = xk*Math.cos(Big_onigak)-yk*Math.cos(ik)*Math.sin(Big_onigak);
        double Yk = xk*Math.sin(Big_onigak)+yk*Math.cos(ik)*Math.cos(Big_onigak);
        double Zk = yk*Math.sin(ik);

        experimentResult.setResult19(Double.valueOf(Xk+","+Yk+","+Zk));

        Map<String, Object> data = new HashMap<>();
        data.put("input", t);
        data.put("output", experimentResult);

        return  AjaxResult.success(data);
    }


    public Date newDate() throws ParseException {
        String beginDate = "2000-01-01";
        String endDate = "2021-10-31";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date start = format.parse(beginDate);
        Date end = format.parse(endDate);
        long rtn = start.getTime() + (long)(Math.random() * (end.getTime() - start.getTime()));
       // Date date = new Date(rtn);
        /*System.out.println(date.getTime());
        String time = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(date);*/
        return  new Date(rtn);
    }

    /**
     * @param //date_ref :参考日期时间
     * @param //date_now ：当前日期时间
     */
    @PostMapping("/exp7_dianwen")
    public AjaxResult exp7_dianwen( ) throws ParseException {

        Date  date_now =  newDate();
        Date  date_ref = new Date();
        ExperimentResult  experimentResult = new ExperimentResult();

        double tk = (date_now.getTime()-date_ref.getTime())/1000.;

        double root_A = 5.282622985840*1000;
        // 长半轴
        double A = root_A*root_A;

        double u = 3.986004418*Math.pow(10,14);
        // 平均运动角速度
        double n0 = Math.sqrt(u/Math.pow(A,3));

        // 平近角点
        double M0 = 6.138553273441*0.1;
        double Mk = M0 + n0*tk;

        // 偏近点角
        double e = 2.297864761204*Math.pow(10,-11);

        double B1 = Mk;
        double B2;
        while (true)
        {
            B2 = Mk - e*Math.sin(B1);
            if (Math.abs(B2 - B1) <= 0.0000000001)
                break;
            B1 = B2;
        }
        double Ek = B2;

        double c = 3.*Math.pow(10,8);
        double F = -2*Math.sqrt(u)/(c*c);
        double delta_tr = F*e*root_A*Math.sin(Ek);
//        北斗
        double ba0 = 0.000471252016723005;
        double ba1 = 2.61479726455351*Math.pow(10,-12);
        double ba2 = 4.00558305466653*Math.pow(10,-25);
        double bdt_tsv = ba0 + ba1*tk+ba2*tk*tk +delta_tr;
        experimentResult.setResult1(bdt_tsv);

//        gps
        double ga0 = 0.000471252016723005;
        double ga1 = 2.61479726455351*Math.pow(10,-12);
        double ga2 = 4.00558305466653*Math.pow(10,-25);
        double gpst_tsv = ga0 + ga1*tk+ga2*tk*tk +delta_tr;
        experimentResult.setResult2(gpst_tsv);

        Map<String, Object> data = new HashMap<>();

        data.put("input",date_ref);
        data.put("input2", date_now);
        data.put("output", experimentResult);

        return  AjaxResult.success(data);
    }

    /**
     * @param //ref_xyz 参考点
     * @param //wx_xyz  卫星坐标
     * @param //user_xyz 用户坐标
     * @param //date_now 当前时间
     * @param  //B1I_f B1I 载波频率  1561.098
     * @param  //B2I_f B2I 载波频率  1207.14
     * @param  //B3I_f B3I 载波频率  1268.52
     */
    @PostMapping("/exp8_gps_dianLiCeng")
    public AjaxResult exp8_gps_dianLiCeng() {

        PointXYZ ref_xyz = new PointXYZ();
        ref_xyz.setX(-63541258.65235);
        ref_xyz.setY(56214563.85123);
        ref_xyz.setZ(459563.41562);

        PointXYZ wx_xyz = new PointXYZ();
        wx_xyz.setX(-13364075.20873);
        wx_xyz.setY(22879822.95064);
        wx_xyz.setZ(790480.10088);

        PointXYZ user_xyz = new PointXYZ();
        user_xyz.setX(-2148744.3969);
        user_xyz.setY(4426641.2099);
        user_xyz.setZ(4044655.8564);



        double B1I_f = 1561.098*Math.pow(10,6);
        double B2I_f =  1207.14*Math.pow(10,6);
        double B3I_f = 1268.52*Math.pow(10,6);

        Date  date_now = new Date();


        ExperimentResult  experimentResult = new ExperimentResult();

        PointXYZ pointENU_wx = this.XYZ2ENUClass(ref_xyz, wx_xyz);
        PointXYZ pointENU_user = this.XYZ2ENUClass(ref_xyz, user_xyz);
        double delta_u = pointENU_wx.u - pointENU_user.u;
        double delta_e = pointENU_wx.e - pointENU_user.e;
        double delta_n = pointENU_wx.n - pointENU_user.n;
//        高度角
        double sita = Math.asin(delta_u/Math.sqrt(delta_e*delta_e+delta_n*delta_n+delta_u*delta_u));
//        方位角
        double alpha=Math.atan(delta_e/delta_n);
        experimentResult.setResult1(sita);
        experimentResult.setResult2(alpha);

        PointBLH pointBLH = this.XYZToBLHClass(user_xyz);
        double user_l = pointBLH.getL(); // 经度
        double user_b = pointBLH.getB(); // 纬度；
        double E = sita;
        double R = 6378;
        double h = 375;
        double baga = Math.PI/2.0-E-Math.asin(R/(R+h)*Math.cos(E));

//        穿刺经度（l）、纬度（b)
        double chuanCi_b = Math.asin(Math.sin(user_b)*Math.cos(baga)+Math.cos(user_b)*Math.sin(baga)*Math.cos(alpha));
        double chuanCi_l = user_l + Math.asin(Math.sin(baga)*Math.sin(alpha)/Math.cos(chuanCi_b));
        experimentResult.setResult3(chuanCi_l);
        experimentResult.setResult4(chuanCi_b);

        double a0 = 0.2235*Math.pow(10,-7);
        double a1 = 0.1490*Math.pow(10,-7);
        double a2 = -0.1192*Math.pow(10,-6);
        double a3 = -0.5960*Math.pow(10,-7);
        double b0 = 0.1249*Math.pow(10,6);
        double b1 = 0.6554*Math.pow(10,5);
        double b2 = -0.1966*Math.pow(10,6);
        double b3 = 0.6554*Math.pow(10,5);

        double A2_base = Math.abs(chuanCi_b/Math.PI);
        double A2 = a0 + a1*A2_base + a2*A2_base*A2_base + a3*A2_base*A2_base*A2_base;
        if(A2<0){
            A2 = 0;
        }
        experimentResult.setResult5(A2);

        double A4 = b0 + b1*A2_base + b2*A2_base*A2_base + b3*A2_base*A2_base*A2_base;
        if(A4>172800){
            A4 = 172800;
        }else if(A4<72000){
            A4 = 72000;
        }
        experimentResult.setResult6(A4);

        // 垂直电离层修改参数
        long te_long = (long)(date_now.getTime()/1000.+chuanCi_l*43200/Math.PI);
        double te = (double)(te_long%86400);
        double It;
        if(Math.abs(te-50400)<A4/4){
            It = 5*Math.pow(10,-9) + A2*Math.cos((2*Math.PI*(te-50400))/A4);
        }else{
            It = 5*Math.pow(10,-9);
        }
        experimentResult.setResult7(It);

        double IB1I = It/Math.sqrt(1-Math.pow(R/(R+h)*Math.cos(E), 2));
        experimentResult.setResult8(IB1I);


        double k12 = B1I_f*B1I_f/(B2I_f*B2I_f);
        double k13 = B1I_f*B1I_f/(B3I_f*B3I_f);
        experimentResult.setResult9(k12);
        experimentResult.setResult10(k12);


        double IB2I  = IB1I*k12;
        double IB3I  = IB1I*k13;
        experimentResult.setResult11(IB2I);
        experimentResult.setResult12(IB3I);

        Map<String, Object> data = new HashMap<>();
        data.put("input", wx_xyz);
        data.put("input2",user_xyz);
        data.put("output", experimentResult);

        return  AjaxResult.success(experimentResult);
    }


    /**
     * 功能描述: 奇数阶乘
     * 〈非递归方法实现阶乘函数〉
     *
     * @params : [num]
     * @return : int
     * @author : cwl
     */
    public static int unRecursion(int n){
        int jiecheng = 1;
        if(2*n-1 == -1){
            return 1;
        }
        for (int i = 1; i <= 2*n-1; i +=2) {
            jiecheng*=i;
        }
        return jiecheng;
    }

    /**
     * 功能描述: 阶乘
     * 〈非递归方法实现阶乘函数〉
     *
     * @params : [num]
     * @return : int
     * @author : cwl
     */
    public static int recursion_1(int num){
        if(num == 1){
            return 1;
        }else if(num == 2){
            return 2;
        }else{
            return num*recursion_1(num-1);
        }
    }


    public double lerangde(int n, int m, double sin_fai_pei){
        if(m==0 && n==0){
            return 1.0;
        }

        if(n==m){
            return unRecursion(n)*Math.pow(1-sin_fai_pei*sin_fai_pei,n/2.0);
        }

        if(n==m+1){
            return sin_fai_pei*(2*m+1)*lerangde(m, m, sin_fai_pei);
        }

        double lerangde_nm = ( (2*n-1)*sin_fai_pei*lerangde(n-1, m, sin_fai_pei) - (n+m-1)*lerangde(n-2,m,sin_fai_pei) )/(n-m);
        return lerangde_nm;

    }

    public double calN(int n, int m){
        double jc1 = recursion_1(n-m);
        double jc2 = recursion_1(n+m);
        double delta0M=0;
        if(m==0){
            delta0M = 1;
        }else if(m>0){
            delta0M = 0;
        }

        double Nnm = Math.sqrt(jc1*(2*n+1)*(2-delta0M)/jc2);

        return Nnm;
    }



    /**
     * @param //ref_xyz 参考点
     * @param //wx_xyz  卫星坐标
     * @param //user_xyz 用户坐标
     * @param //date_now 当前时间
     * @param //B1I_f B1I 载波频率
     * @param //B2I_f B2I 载波频率
     * @param //B3I_f B3I 载波频率
     * @param //alpha : 这个是由接收机发送过来的9个参数；
     */
    @PostMapping("/exp9_bds_dianLiCeng")
    public AjaxResult exp9_bds_dianLiCeng( double[] alpha_arr) {

        PointXYZ ref_xyz = new PointXYZ();
        ref_xyz.setX(-63541258.65235);
        ref_xyz.setY(56214563.85123);
        ref_xyz.setZ(459563.41562);

        PointXYZ wx_xyz = new PointXYZ();
        wx_xyz.setX(-13364075.20873);
        wx_xyz.setY(22879822.95064);
        wx_xyz.setZ(790480.10088);

        PointXYZ user_xyz = new PointXYZ();
        user_xyz.setX(-2148744.3969);
        user_xyz.setY(4426641.2099);
        user_xyz.setZ(4044655.8564);



        double B1I_f = 1561.098*Math.pow(10,6);
        double B2I_f =  1207.14*Math.pow(10,6);
        double B3I_f = 1268.52*Math.pow(10,6);

        Date  date_now = new Date();

        ExperimentResult  experimentResult = new ExperimentResult();

        PointXYZ pointENU_wx = this.XYZ2ENUClass(ref_xyz, wx_xyz);
        PointXYZ pointENU_user = this.XYZ2ENUClass(ref_xyz, user_xyz);
        double delta_u = pointENU_wx.u - pointENU_user.u;
        double delta_e = pointENU_wx.e - pointENU_user.e;
        double delta_n = pointENU_wx.n - pointENU_user.n;
//        高度角
        double sita = Math.asin(delta_u/Math.sqrt(delta_e*delta_e+delta_n*delta_n+delta_u*delta_u));
//        方位角
        double alpha=Math.atan(delta_e/delta_n);
        experimentResult.setResult1(sita);
        experimentResult.setResult2(alpha);

        PointBLH pointBLH = XYZToBLHClass(user_xyz);
        double user_l = pointBLH.getL(); // 经度
        double user_b = pointBLH.getB(); // 纬度；
        double E = sita;
        double R = 6378;
        double h = 400;
        // 地心张角
        double baga = Math.PI/2.0-E-Math.asin(R/(R+h)*Math.cos(E));
        experimentResult.setResult3(baga);

//        穿刺点的地理经度（l）、纬度（b)
        double chuanCi_b = Math.asin(Math.sin(user_b)*Math.cos(baga)+Math.cos(user_b)*Math.sin(baga)*Math.cos(alpha));
        double chuanCi_l = user_l + Math.asin(Math.sin(baga)*Math.sin(alpha)/Math.cos(chuanCi_b));
        double faig = chuanCi_b;
        double simag = chuanCi_l;
        experimentResult.setResult4(simag);
        experimentResult.setResult5(faig);

//      提示用户计算穿刺点的地心地固直角坐标系磁经纬度
        double faiM = 80.27/180*Math.PI;
        double simaM = -72.58/180*Math.PI;
        // 输出
        double fai_m = Math.asin(Math.sin(faiM)*Math.sin(faig)+Math.cos(faiM)*Math.cos(faig)*Math.cos(simag-simaM));
        double sima_m = Math.atan(Math.cos(faig)*Math.sin(simag-simaM)*Math.cos(faiM)/(Math.sin(faiM)*Math.sin(fai_m)-Math.sin(faig)));


        // 穿刺点的日固地磁经纬度
        double tt = date_now.getTime()/1000;
        double tt_hour = date_now.getHours();
        double Slon = Math.PI*(1-2*(tt-Math.floor(tt)));
        double fai_pei = fai_m;
        double sima_pei = sima_m - Math.atan(Math.sin(Slon)/(Math.sin(fai_m)*Math.cos(Slon)));
        double sin_fai_pei = Math.sin(fai_pei);
        experimentResult.setResult6(fai_pei);
        experimentResult.setResult7(sima_pei);

//        A1~A9
        int[] n_arr = {0,1,1,1,2,2,2,2,2};
        int[] m_arr = {0,0,1,-1,0,1,-1,2,-2};
        double[] Ai = new double[9];
        for(int idx=0; idx<9; idx++){
            int ni=n_arr[idx];
            int mi=m_arr[idx];
            if(mi<0){
                int ni_abs = Math.abs(ni);
                int mi_abs = Math.abs(mi);
                double Nnm = calN(ni_abs, mi_abs);
                double Pnm = lerangde(ni_abs, mi_abs, sin_fai_pei);
                Ai[idx] = Pnm*Nnm*Math.sin(mi_abs*sima_pei);
            }else{
                int ni_abs = Math.abs(ni);
                int mi_abs = Math.abs(mi);
                double Nnm = calN(ni_abs, mi_abs);
                double Pnm = lerangde(ni_abs, mi_abs, sin_fai_pei);
                Ai[idx] = Pnm*Nnm*Math.cos(sima_pei);
            }
        }
        experimentResult.setResult21(Ai);
//       A0
        double tp = tt_hour;
        int[] n_arr0 = {3,3,3,3,3,3,3,4,4,4,4,4,5,5,5,5,5};
        int[] m_arr0 = {0,1,-1,2,-2,3,-3,0,1,-1,2,-2,0,1,-1,2,-2};

        double[] a0j = {-0.61, -1.31, -2.00, -0.03, 0.15, -0.48, -0.40, 2.28, -0.16, -0.21, -0.10, -0.13, 0.21, 0.68, 1.06, 0, -0.12};

        double [][] akj = {{-0.51,-0.43 ,0.34 ,-0.01, 0.17 ,0.02 ,-0.06, 0.30, 0.44, -0.28, -0.31 ,-0.17 ,0.04 ,0.39, -0.12, 0.12, 0},
                {-0.06, -0.05, 0.06, 0.17, 0.15, 0, 0.11, -0.05, -0.16, 0.02 ,0.11, 0.04, 0.12 ,0.07, 0.02, -0.14, -0.14},
                {0.01 ,-0.03, 0.01 ,-0.01, 0.05, -0.03, 0.05, -0.03, -0.01 ,0 ,-0.08, -0.04 ,0, -0.02, -0.03, 0 ,-0.03},
                {-0.01, 0, 0.01 ,0, 0.01 ,0, -0.01, -0.01, 0 ,0, 0, 0, 0, 0, 0, 0, 0},
                {0 ,0 ,0.03 ,0.01 ,0.02 ,0.01 ,0 ,-0.02 ,0 ,0 ,0 ,0, 0 ,0 ,0 ,0 ,0},
                {-0.19, -0.02, 0.12, -0.10, 0.06, 0, -0.02 ,-0.08, -0.02, -0.07, 0.01, 0.03 ,0.15, 0.06, -0.05 ,-0.03, -0.10},
                {-0.18, 0.06 ,-0.55 ,-0.02, 0.09, -0.08 ,0, 0.86, -0.18 ,-0.05 ,-0.07,0.04 ,0.14, -0.03, 0.37, -0.11, -0.12},
                {1.09 ,-0.14 ,-0.21, 0.52, 0.27, 0 ,0.11, 0.17 ,0.23, 0.35 ,-0.05, 0.02, -0.60, 0.02, 0.01, 0.27, 0.32},
                {-0.34, -0.09, -1.22 ,0.05 ,0.15 ,-0.29 ,-0.17 ,1.58 ,-0.06 ,-0.15 ,0.00 ,0.13 ,0.28 ,-0.08 ,0.62 ,-0.01 ,-0.04},
                {-0.13, 0.07, -0.37, 0.05, 0.06, -0.11, -0.07, 0.46, 0.00, -0.04, 0.01, 0.07, 0.09, -0.05, 0.15, -0.01, 0.01},
                {-0.06 ,0.13 ,-0.07 ,0.03 ,0.02 ,-0.05 ,-0.05 ,0.01 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
                {-0.03 ,0.08 ,-0.01 ,0.04, 0.01, -0.02 ,-0.02, -0.04, 0 ,0, 0, 0, 0, 0, 0, 0, 0}
        };

        double [][] bkj = {{0.23 ,-0.20, -0.31, 0.16, -0.03, 0.02, 0.04, 0.18, 0.34, 0.45, 0.19, -0.25, -0.12, 0.18 ,0.40, -0.09, 0.21},
                {0.02 ,-0.08 ,-0.06 ,-0.11 ,0.15 ,-0.14 ,0.01 ,0.01 ,0.04 ,-0.14 ,-0.05 ,0.08 ,0.08 ,-0.01 ,0.01 ,0.11 ,-0.12},
                {0 ,-0.02, -0.03, -0.05, -0.01, -0.07, -0.03, -0.01 ,0.02, -0.01, 0.03, -0.10, 0.01, 0.05, -0.01, 0.04 ,0.00},
                {0 ,-0.02, 0.01, 0, -0.01, 0.01 ,0 ,-0.02, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0.01, 0 ,0 ,0.01 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
                {-0.09, 0.07, 0.03, 0.06, 0.09, 0.01, 0.02, 0, -0.04, -0.02, -0.01, 0.01, -0.10, 0, -0.01, 0.02, 0.05},
                {0.15 ,-0.31, 0.13, 0.05, -0.09, -0.03, 0.06, -0.36, 0.08, 0.05, 0.06, -0.02, -0.05, 0.06, -0.20, 0.04, 0.07},
                {0.50 ,-0.08 ,-0.38 ,0.36 ,0.14 ,0.04 ,0 ,0.25 ,0.17, 0.27 ,-0.03 ,-0.03 ,-0.32, -0.10 ,0.20 ,0.10 ,0.30},
                {0 ,-0.11, -0.22, 0.01, 0.02, -0.03, -0.01, 0.49, -0.03, -0.02, 0.01, 0.02, 0.04, -0.04, 0.16, -0.02, -0.01},
                {0.05 ,0.03 ,0.07 ,0.02 ,-0.01 ,0.03 ,0.02 ,-0.04 ,-0.01 ,-0.01 ,0.02, 0.03, 0.02 ,-0.04 ,-0.04 ,-0.01, 0},
                {0.03 ,-0.02, 0.04, -0.01, -0.03, 0.02, 0.01, 0.04, 0 ,0 ,0, 0, 0, 0, 0, 0, 0},
                {0.04 ,-0.02 ,-0.04 ,0.00 ,-0.01 ,0 ,0.01 ,0.07 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0}
        };

        double[] Tk = {1, 0.5,0.33,14.6,27.0, 121.6, 182.51,365.25,4028.71,2014.35,1342.9,1007.18};
        double[] beta = new double[17];
        for(int j=0; j<17; j++){
            beta[j] = a0j[j];
            for(int k=0; k<12; k++){
                double wk = 2*Math.PI/Tk[k];
                beta[j] += (akj[k][j]*Math.cos(wk*tp)+bkj[k][j]*Math.sin(wk*tp));
            }
        }

        double[] Bj = new double[17];
        for(int idx=0; idx<17; idx++){
            int ni=n_arr[idx];
            int mi=m_arr[idx];
            if(mi<0){
                int ni_abs = Math.abs(ni);
                int mi_abs = Math.abs(mi);
                double Nnm = calN(ni_abs, mi_abs);
                double Pnm = lerangde(ni_abs, mi_abs, sin_fai_pei);
                Bj[idx] = Pnm*Nnm*Math.sin(mi_abs*sima_pei);
            }else{
                int ni_abs = Math.abs(ni);
                int mi_abs = Math.abs(mi);
                double Nnm = calN(ni_abs, mi_abs);
                double Pnm = lerangde(ni_abs, mi_abs, sin_fai_pei);
                Bj[idx] = Pnm*Nnm*Math.cos(mi_abs*sima_pei);
            }
        }

        double A0=0;
        for(int idx=0; idx<17; idx++){
            A0 += beta[idx]*Bj[idx];
        }
        experimentResult.setResult8(A0);

        // 穿刺点VTEC
        double VTEC = A0;
        for(int idx=0; idx<9; idx++){
            VTEC += alpha_arr[idx]*Ai[idx];
        }
        experimentResult.setResult9(VTEC);

        // 电离层投影函数Mf
        double MF = 1/Math.sqrt(1-Math.pow(R/(R+h)*Math.cos(E), 2) );
        experimentResult.setResult7(MF);

//      提示用户计算斜路径上电离层延迟改正值
        double Tion = (MF*40.28*Math.pow(10,16)/(Math.pow(B1I_f,2)) )*VTEC;
        experimentResult.setResult10(Tion);

//        转换因子
        double k12 = B1I_f*B1I_f/(B2I_f*B2I_f);
        double k13 = B1I_f*B1I_f/(B3I_f*B3I_f);
        experimentResult.setResult11(k12);
        experimentResult.setResult12(k13);

//        得到BDS的电离层改正值
        double Tion_IB2I  = Tion*k12;
        double Tion_IB3I  = Tion*k13;
        experimentResult.setResult13(Tion_IB2I);
        experimentResult.setResult14(Tion_IB3I);

        Map<String, Object> data = new HashMap<>();
        data.put("input", wx_xyz);
        data.put("input2",user_xyz);
        data.put("output", experimentResult);

        return  AjaxResult.success(data);
    }

    /**
     * 接收机接受参数
     * @param  //B1I_f : 载波频率
     * @param //B3I_f ：载波频率
     * @param //P1 : 伪距观测值
     * @param //P3： 伪距观测值；
     * @param // Tgd
     */
    @PostMapping("/exp10_shuangpinDianLiCeng")
    public AjaxResult exp10_shuangpinDianLiCeng() {

        double B1I_f =  1561.098*Math.pow(10,6);
        double B3I_f = 1268.52*Math.pow(10,6);
        double P1 =27037228.362;
        double P3 = 27037237.967;
        double Tgd = -1.97*Math.pow(10,-8);


        ExperimentResult  experimentResult = new ExperimentResult();
        double c = 3.*Math.pow(10,8);

//        伪距修正值
        double PR1 = P1 - c*Tgd;
        double PR3 = P3 - c*Tgd;
        experimentResult.setResult1(PR1+PR3);

        double df1 = B1I_f*B1I_f;
        double df3 = B3I_f*B3I_f;


//        电离层延迟值
        double I1 = df3/(df3-df1)*(P1-P3);
        double I3 = df1/(df3-df1)*(P1-P3);
        experimentResult.setResult2(I1);
        experimentResult.setResult3(I3);
        Map<String, Object> data = new HashMap<>();
        data.put("input", B1I_f+","+B3I_f);
        data.put("input2",Tgd);
        data.put("output", experimentResult);

        return  AjaxResult.success(experimentResult);
    }

}
