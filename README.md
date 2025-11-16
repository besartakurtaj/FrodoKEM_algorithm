
<table border="0">
 <tr>
    <td><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/University_of_Prishtina_logo.svg/1200px-University_of_Prishtina_logo.svg.png" width="150" alt="University Logo" /></td>
    <td>
      <p>University of Pristina</p>
      <p>Faculty of Electrical and Computer Engineering</p>
      <p>Computer and Software Engineering - Master Program</p>
	<p>Course: Information Security</p>
      <p>Professor: Dr. Sc. Mërgim Hoti</p>
    </td>
 </tr>
</table>


# Zbatimi i FrodoKEM për Enkapsulimin e Çelësave me Gabime në Java

## Përmbledhje
Ky projekt demonstron implementimin e **FrodoKEM**, një mekanizëm post-kuantik për enkapsulimin e çelësave (Key Encapsulation Mechanism – KEM) duke përdorur bibliotekën **BouncyCastle PQC** në Java. FrodoKEM mbështetet në vështirësinë e problemit **Learning With Errors (LWE)**, i cili konsiderohet i pa-zgjidhshëm edhe nga kompjuterët kuantikë.

Implementimi përfshin:
- Gjenerimin e çelësave (public/private)
- Enkapsulimin e çelësit të përbashkët
- Dekapsulimin e çelësit
- Verifikimin me constant-time comparison
- Ruajtjen e çelësave dhe ciphertext në skedarë

---

## Çfarë është FrodoKEM?
**FrodoKEM** është një mekanizëm shkëmbimi çelësash i rezistent ndaj sulmeve kuantike. Ai mbështetet në problemin LWE:

A * s + e (mod q)

Ku:
- **A** – matricë publike
- **s** – vektori sekret
- **e** – gabimi i shpërndarë Gaussianisht (noise)
- **q** – moduli

Problemi është ekstremisht i vështirë për tu zgjidhur → ndaj garanton **post-quantum security**.

---

## Si funksionon FrodoKEM?

### **1️. Gjenerimi i çelësave**
- Gjenerohet matrica A
- Gjenerohet sekreti s
- Llogaritet b = A·s + e  
Rezultati:  
- **Public key = (A, b)**
- **Private key = s**

### **2️. Enkapsulimi**
Pala dërguese gjeneron një sekret të ri s′ dhe llogarit ciphertext:

c1 = A * s' + e1  
c2 = b * s' + e2 + hash(s')

### **3️. Dekapsulimi**
Pala marrëse përdor çelësin privat për rikrijimin e s′ dhe shared secret.  

b * s' = (A * s + e) * s'  

---  

## Struktura e Projektit

```
FrodoKEM_algorithm/
│
├── lib/
│   ├── bcprov-jdk18on-1.79.jar
│   └── bcpqc-jdk18on-1.79.jar
│
├── src/main/java/com/example/
│   ├── Main.java
│   ├── FrodoKEM.java
│   └── FileUtils.java
│
└── output/
    ├── public.key
    ├── private.key
    └── ciphertext.bin
```

---

## Instalimi dhe Ekzekutimi

### **1️. Kërkesat**
Vendos bibliotekat në folderin:

```
lib/
    bcprov-jdk18on-1.82.jar
    bcutil-jdk18on-1.82.jar
```

### **2️. Kompajllim**

```bash
javac -cp "lib/*" src/main/java/com/example/*.java
```

### **3️. Ekzekutimi**

```bash
java -cp "lib/*;src/main/java" com.example.Main
```

---

## Rezultatet (Nga Ekzekutimi Real)

```
=== FrodoKEM Demonstration ===
Using parameter set: frodokem640aes

Key generation took: 43 ms
Public key length: 9616 bytes
Private key length: 19888 bytes

Encapsulation took: 11 ms
Ciphertext length: 9720 bytes

Decapsulation took: 9 ms

Shared key (encapsulator): a70d747dbf41d0a32bf4e9f25c78e885
Shared key (decapsulator): a70d747dbf41d0a32bf4e9f25c78e885

Keys match!
```

### Analizë:

| Procesi | Koha | Koment |
|--------|------|---------|
| KeyGen | **43 ms** | Shumë mirë për FrodoKEM-640 |
| Encapsulation | **11 ms** | Gjenerim i shpejtë |
| Decapsulation | **9 ms** | Rikthim perfekt i shared secret |
| Public key | **9616 bytes** | Madhësi standarde |
| Ciphertext | **9720 bytes** | Madhësi standarde |

### Përfundim:
**Shared secret përputhet 100% → implementimi i FrodoKEM është i saktë dhe funksional.**

---

## Siguria Post-Kuantike

FrodoKEM siguron:
- Mbrojtje ndaj sulmeve me kompjuterë kuantikë  
- Nuk përdor struktura të rrezikshme matematike  
- Vështirësi të lartë bazuar në LWE  
- Siguri të gjatë për të dhëna kritike  

---

Authors:
        Besarta Kurtaj,
        Enis Hoxha,
        Shefket Bylygbashi
Year: 2025
