# App de gestión para estudio de tatuajes

### Supuesto a resolver
___
Deseamos diseñar una aplicación que guarde la información necesaria para la gestión
de un estudio de tatuajes. Los supuestos considerados son los siguientes:

*Citas*
___

En nuestro estudio, cada cita incluye una breve descripción para identificar el trabajo, la fecha y hora programadas, el estado actual de la cita (por ejemplo: reservada o realizada). También registramos la duración aproximada del servicio, el precio total del trabajo y el depósito que se deja como señal para confirmar la cita. Además, distinguimos si la cita es para un tatuaje o un piercing.

Las citas de tatuaje deben ser realizadas por un tatuador, mientras que las citas de piercing requieren la intervención de un anillador. Un tatuador o anillador puede encargarse de varias citas a lo largo del día. Además, cada cita debe realizarse en una única cabina, aunque una cabina puede albergar múltiples citas en un mismo día. Por otro lado, cada cita debe estar asignada a uno o más clientes, y cada cliente puede tener varias citas reservadas o realizadas.

En las citas de tatuaje, es posible que se utilicen uno o más tipos de agujas, y cada tipo específico de aguja puede ser utilizado en diferentes citas según sea necesario.

*Trabajadores*
___
Llevamos un registro de nuestros trabajadores con su nombre completo, NIF, número de la seguridad social, fecha de inicio en el equipo, fecha de nacimiento, dirección completa, correo electrónico y uno o más números de teléfono. Además, podemos incluir su salario, aunque no siempre es obligatorio, ya que algunos reciben su pago basado en un porcentaje del trabajo realizado y no tienen sueldo fijo.

Algunos trabajadores pueden desempeñarse como tatuadores y otros como anilladores, o incluso realizar ambas funciones. Ninguno de ellos tiene un salario fijo; en su lugar, acordamos un porcentaje del precio de cada cita que se llevan como compensación por su trabajo.

*Clientes*
___
Para cada cliente, recopilamos su nombre completo, DNI, dirección completa, fecha de nacimiento y un teléfono de contacto. Opcionalmente, también podemos registrar su Instagram y correo electrónico si desean compartirlos. Por último, tomamos nota de cualquier alergia que puedan tener, para garantizar la seguridad durante los procedimientos, teniendo en cuenta que es posible que un mismo cliente sufra de varias alergias.

En el caso de clientes menores de edad, es obligatorio registrar un tutor legal que autorice a la realización del tatuaje. Tambíen queremos registrar la relación de parentesco entre el cliente menor y el cliente tutor legal.

*Cabinas*
___
Nuestro estudio cuenta con varias cabinas donde se realizan las citas. De cada cabina, registramos un número de identificación único, su ubicación dentro del estudio y el tamaño de su superficie. Algunas cabinas están especialmente acondicionadas para realizar piercings, mientras que existen cabinas donde no se pueden llevar a cabo.

Cada cabina cuenta con diversos productos necesarios para llevar a cabo los procedimientos. Queremos mantener un inventario o stock donde además de registrar el nombre de cada producto, llevemos un seguimiento de su fecha de caducidad y la cantidad disponible de producto en cada una de las cabinas, asegurándonos de que todo esté en óptimas condiciones para su uso.

Cada cabina está dividida en varios huecos que nos ayudan a gestionar la disponibilidad de las citas. Cada hueco tiene un número de identificación, que se compone de su número único junto con el identificador de la cabina a la que pertenece. Para cada hueco registramos la hora, el día y si está disponible o no para una fecha y hora específica. Esto nos permite organizar el calendario de manera eficiente y garantizar que haya espacio para cada cliente.


*Agujas*
___

En el caso de los tatuajes, utilizamos agujas especializadas. E problema es que estas agujas no se guardan en relación al stock de una cabina, sino que se mantienen aparte. Para cada tipo de aguja, registramos un identificador único, el número de puntas que tiene la aguja, su configuración (la disposición de estas), la longitud del taper, y cuántas están disponibles en el inventario. El tipo de aguja se define combinando el número de agujas, su configuración y el taper. No permitimos duplicados, por lo que no puede haber dos agujas registradas con las mismas características.


Las citas de tatuaje deben ser realizadas por un tatuador, mientras que las citas de piercing requieren la intervención de un anillador. Un tatuador o anillador puede encargarse de varias citas a lo largo del día. Además, cada cita debe realizarse en una única cabina, aunque una cabina puede albergar múltiples citas en un mismo día. Por otro lado, cada cita debe estar asignada a uno o más clientes, y cada cliente puede tener varias citas reservadas o realizadas.

En las citas de tatuaje, es posible que se utilicen uno o más tipos de agujas, y cada tipo específico de aguja puede ser utilizado en diferentes citas según sea necesario.