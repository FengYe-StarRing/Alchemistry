package al132.alchemistry.chemistry;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ChemicalElement implements ICompoundComponent {
    public final String name;
    public final String abbreviation;
    public final Color color;
    public List<String> materials;
    public int burnTime = 0;

    public ChemicalElement(String name,String abbreviation,Color color) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.color = color;
        materials = Collections.singletonList("ingot");
    }

    public ChemicalElement(String name,String abbreviation,Color color,String[] materials) {
        this(name,abbreviation,color);
        this.materials = Arrays.asList(materials);
    }

    @NotNull
    @Override
    public String toAbbreviatedString() {
        return abbreviation;
    }

    public ChemicalElement setBurnTime(int burnTime) {
        this.burnTime = burnTime;
        return this;
    }

    public int getAtomicNumber() {
        return ElementRegistry.getAtomicNumber(name);
    }

    /**
     * 从原子序数通过 Aufbau 原理计算电子构型。
     * 返回 int[]{fn, fl, fe, totalD, sOuter, sCurr}:
     *   fn = 最外层主量子数
     *   fl = 最外层角量子数 (0=s, 1=p, 2=d, 3=f)
     *   fe = 最外层该轨道上的电子数
     *   totalD = 所有 d 轨道的电子总数
     *   sOuter = (fn+1)层的 s 电子数（用于算族号）
     *   sCurr = fn层的 s 电子数（用于算外层总电子）
     */
    public int[] getAufbau() {
        int z = getAtomicNumber();
        int r = z,sum = 1,fn = 0,fl = -1,fe = 0,totalD = 0;
        Map<Integer,Integer> sMap = new HashMap<>();
        while(r > 0) {
            int nStart = sum / 2 + 1;
            for(int n = nStart;n <= sum;n++) {
                int l = sum - n;
                if(l >= n) continue;
                int cap = 2 * (2 * l + 1);
                int fill = Math.min(r,cap);
                if(l == 0) sMap.put(n,fill);
                if(l == 2) totalD += fill;
                r -= fill;
                fn = n;fl = l;fe = fill;
                if(r == 0) break;
            }
            sum++;
        }
        return new int[]{fn,fl,fe,totalD,sMap.getOrDefault(fn + 1,0),sMap.getOrDefault(fn,0)};
    }

    public boolean canBeCathode() {
        int[] a = getAufbau();
        int fn = a[0],fl = a[1],fe = a[2],sCurr = a[5];
        if(fl == 2 || fl == 3) return false;
        int outerTotal = fl == 0 ? fe : sCurr + fe;
        int target = fn == 1 ? 2 : 8;
        int gain = target - outerTotal;
        int lose = outerTotal;
        return lose > 0 && lose < gain;
    }

    public boolean canBeAnode() {
        int[] a = getAufbau();
        int fn = a[0],fl = a[1],fe = a[2],totalD = a[3],sCurr = a[5];
        if(fl == 2 && totalD > 0) return true;
        if(fl == 3 && fe >= 1) return true;
        int outerTotal = fl == 0 ? fe : sCurr + fe;
        int target = fn == 1 ? 2 : 8;
        int gain = target - outerTotal;
        int lose = outerTotal;
        return gain > 0 && gain <= lose;
    }

    public int getPeriodicGroup() {
        int[] a = getAufbau();
        int fl = a[1],fe = a[2],sOuter = a[4];
        int z = getAtomicNumber();
        if(fl == 0) return z == 2 ? 18 : fe;
        if(fl == 1) return 12 + fe;
        if(fl == 2) return sOuter + fe;
        return 3;
    }

    public int getVoltageFactor() {
        int group = getPeriodicGroup();
        if(group == 1) return 15;
        if(group == 2) return 12;
        if(group >= 3 && group <= 12) return 4;
        if(group == 13) return 5;
        if(group == 14 || group == 16) return 1;
        if(group == 15) return 3;
        if(group == 17) return 10;
        return 0;
    }

    public int getOuterElectron() {
        int[] a = getAufbau();
        int fl = a[1],fe = a[2],sCurr = a[5],sOuter = a[4];
        if(fl == 0) return fe;
        if(fl == 1) return sCurr + fe;
        if(fl == 2) return sOuter + fe;
        if(fl == 3) return sOuter + fe;
        return 0;
    }
}
