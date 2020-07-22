clear all;
//rijvectoren x en y
thresh = 1;

x=[965,-728,-501,-754];
y=[38763843559201256719504921956401473,-16718676163400778611014727673372769,-5455360391806671072802792482957447,-18573060224910146455840427757587789];

X=[];
x=x';
y=y';

XYM=[x,y];
for(i=1:length(x)-1)
    for(j=i+1:length(x))
        if(XYM(i,1)>XYM(j,1))
            tmp=XYM(j,:)
            XYM(j,:)=XYM(i,:)
            XYM(i,:)=tmp
        end
    end
end
x=XYM(:,1)
y=XYM(:,2)
for(i=0:length(x)-1)
    X=[X,x.^i];
end

a=X\y;
for(i=1:length(a))
    printf("x^%i: %d\n",i-1,a(i))
end
printf("\n")
xnewmin=min(x)-100;xnewmax=max(x)+100;newPoints=1000;
xnew = linspace(xnewmin,xnewmax,newPoints);
ynew = zeros(1,length(xnew));
for(i=1:length(a))
    ynew = ynew + a(i)*(xnew.^(i-1));
    printf("%i*(xnew.^(%i))\n",a(i),i-1)
end

scf();clf();
plot(xnew,ynew,'r')
plot(x,y,'x')
h=gca()
if(min(y)<0)
    h.data_bounds = [xnewmin, xnewmax,(12/10)*min(y),(11/10)*max(y)]
else
    h.data_bounds = [xnewmin, xnewmax,(8/10)*min(y),(11/10)*max(y)]
end


printf("\nNorm of residuals: %.5f\n",norm(X*a-y))
if(norm(X*a-y)>thresh)
    printf("ERROR! Residuals are not 0!\nResult unreliable!\n");
else
    printf("\nDecoded number: %d",a(1));
end

bp = linspace(min(x),max(x),length(x)-1)';
[spY,spD] = lsq_splin(x,y,bp)

ys=interp(x,bp,spY,spD)
scf();clf();
plot(x,ys)
